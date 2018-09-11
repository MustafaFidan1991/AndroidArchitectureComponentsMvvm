package com.detoksdiyetleri.data.repository

import android.arch.lifecycle.LiveData
import com.detoksdiyetleri.data.room.DietEntity
import com.detoksdiyetleri.model.Diet
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

interface IDietRepository {

    fun getFavoritesDiets() : Flowable<List<DietEntity>>

    fun getDietsByPage(pageNumber : Int, latestId : String): Query


    fun setDietToFavorites()

    fun getDietByRemoteId(remoteId:String):Single<List<DietEntity>>

    fun insert(dietEntity: DietEntity)

    fun delete(dietEntity: DietEntity)


}