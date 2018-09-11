package com.detoksdiyetleri.ui.favorites

import com.detoksdiyetleri.base.BaseRecyclerItemViewModel
import com.detoksdiyetleri.data.repository.DietRepository
import com.detoksdiyetleri.data.room.DietEntity
import com.detoksdiyetleri.model.Diet
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FavoritesItemViewModel : BaseRecyclerItemViewModel<Diet>() {

    @Inject
    lateinit var dietRepository : DietRepository

    var title : String?=null

    var content : String?=null

    var imgUrl : String?=null

    override fun setValues() {
        title = model.title
        content = model.content
        imgUrl = model.imgUrl
    }

    fun delete(){

        val observable = Observable.create<Boolean> { emitter ->
            dietRepository.deleteByRemoteId(model.id)
            emitter.onNext(false)
            emitter.onComplete()
        }
        compositeDisposable.add(observable.observeOn(
                AndroidSchedulers.mainThread()
        ).subscribeOn(Schedulers.io()).subscribe { result ->})
    }
}