package com.detoksdiyetleri.ui.favorites

import android.arch.lifecycle.MutableLiveData
import com.detoksdiyetleri.base.BaseViewModel
import com.detoksdiyetleri.data.repository.DietRepository
import com.detoksdiyetleri.data.room.DietEntity
import com.detoksdiyetleri.model.Diet
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FavoritesViewModel : BaseViewModel() {

    private var liveDietList: MutableLiveData<MutableList<Diet>>?=null

    @Inject
    lateinit var dietRepository: DietRepository

    fun loadFavoritesDiets() : MutableLiveData<MutableList<Diet>>?{
        if(liveDietList==null){
            liveDietList = MutableLiveData()
            liveDietList?.value = mutableListOf()
        }

        compositeDisposable.add(dietRepository.getFavoritesDiets()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ favoritesList ->
                    liveDietList?.value = transform(favoritesList.toMutableList())
                }, { t: Throwable? -> t?.printStackTrace() })
        )

        return liveDietList
    }

    fun transform(list:MutableList<DietEntity>) : MutableList<Diet>{
        val dietList = ArrayList<Diet>()
        list.forEach {
            dietEntity->
            val diet = Diet()
            diet.id = dietEntity.remoteId
            diet.title = dietEntity.title
            diet.content = dietEntity.content
            diet.imgUrl = dietEntity.dietImgUrl
            diet.createdDate = dietEntity.createdDate
            dietList.add(diet)
        }
        return dietList.toMutableList()
    }

}