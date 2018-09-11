package com.detoksdiyetleri.ui.detail

import android.arch.lifecycle.MutableLiveData
import android.databinding.Bindable
import com.detoksdiyetleri.BR
import com.detoksdiyetleri.base.BaseViewModel
import com.detoksdiyetleri.data.repository.DietRepository
import com.detoksdiyetleri.data.room.DietEntity
import com.detoksdiyetleri.model.Diet
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailViewModel : BaseViewModel() {

    @Inject
    lateinit var dietRepository: DietRepository

    lateinit var diet : Diet

    @Bindable
    var title : String?=null

    @Bindable
    var content : String?=null

    @Bindable
    var imgUrl : String?=null

    var favoriteStatus : MutableLiveData<Boolean>?= null

    fun setValues(){
        title = diet.title
        content = diet.content
        imgUrl = diet.imgUrl

        notifyPropertyChanged(BR.title)
        notifyPropertyChanged(BR.content)
        notifyPropertyChanged(BR.imgUrl)
        isAddedFavorite()
    }


    fun isAddedFavorite(){
        compositeDisposable.add(
                dietRepository
                        .getDietByRemoteId(diet.id)
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
                        .getDietByRemoteId(diet.id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            dietEntity ->
                            if(dietEntity!!.size > 0){
                                delete(dietEntity.first())
                            }
                            else{
                                val de = DietEntity()
                                de.remoteId = diet.id
                                de.content = diet.content
                                de.title = diet.title
                                de.dietImgUrl = diet.imgUrl
                                de.createdDate = diet.createdDate
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