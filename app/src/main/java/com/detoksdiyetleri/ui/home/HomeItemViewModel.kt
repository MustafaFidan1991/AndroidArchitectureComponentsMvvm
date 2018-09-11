package com.detoksdiyetleri.ui.home

import android.arch.lifecycle.MutableLiveData
import android.os.Environment
import com.detoksdiyetleri.R
import com.detoksdiyetleri.base.BaseRecyclerItemViewModel
import com.detoksdiyetleri.data.repository.DietRepository
import com.detoksdiyetleri.data.room.DietEntity
import com.detoksdiyetleri.model.Diet
import com.detoksdiyetleri.utils.DATE_FORMAT
import com.detoksdiyetleri.utils.DateCalculator
import com.detoksdiyetleri.utils.ResourceProvider
import com.detoksdiyetleri.utils.toOutputDateFormat
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.util.*
import javax.inject.Inject

class HomeItemViewModel : BaseRecyclerItemViewModel<Diet>(){

    @Inject
    lateinit var resourceProvider: ResourceProvider

    @Inject
    lateinit var dietRepository: DietRepository

    var createdDate : String?=null

    var title : String?=null

    var content : String?=null

    var imgUrl : String?=null

    var favoriteStatus : MutableLiveData<Boolean> ?= null

    override fun setValues() {
        favoriteStatus = MutableLiveData()
        createdDate = DateCalculator.calculateTime(resourceProvider,model.createdDate, Date().toOutputDateFormat(DATE_FORMAT), DATE_FORMAT)
        title = model.title
        content = model.content
        imgUrl = model.imgUrl
        isAddedFavorite()
    }

    fun isAddedFavorite(){
        compositeDisposable.add(
                dietRepository
                        .getDietByRemoteId(model.id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            dietEntity ->
                            favoriteStatus?.value = dietEntity!!.size > 0
                        }, {
                            t: Throwable? ->
                            t?.printStackTrace()
                        }))
    }

    fun addToFavorite(){

        compositeDisposable.add(
                dietRepository
                .getDietByRemoteId(model.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    dietEntity ->
                    if(dietEntity!!.size > 0){
                        delete(dietEntity.first())
                    }
                    else{
                        val de = DietEntity()
                        de.remoteId = model.id
                        de.content = model.content
                        de.title = model.title
                        de.dietImgUrl = imgUrl!!
                        de.createdDate = createdDate!!
                        insert(de)
                    }


                }, {
                    t: Throwable? ->
                    t?.printStackTrace()
                }))
    }


    fun insert(dietEntity: DietEntity){
        val observable = Observable.create<Boolean> { emitter ->
            dietRepository.insert(dietEntity)
            emitter.onNext(true)
            emitter.onComplete()
        }
        compositeDisposable.add(observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe { result ->
            favoriteStatus?.value = result
        })
    }

    fun delete(dietEntity: DietEntity){
        val observable = Observable.create<Boolean> { emitter ->
            dietRepository.delete(dietEntity)
            emitter.onNext(false)
            emitter.onComplete()
        }
        compositeDisposable.add(observable.observeOn(
                AndroidSchedulers.mainThread()
        ).subscribeOn(Schedulers.io()).subscribe { result ->
            favoriteStatus?.value = result
        })
    }

}