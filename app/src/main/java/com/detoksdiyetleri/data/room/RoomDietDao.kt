package com.detoksdiyetleri.data.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.detoksdiyetleri.model.Diet
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single


@Dao
interface RoomDietDao {


    @Insert
    fun insertAll(diets: List<DietEntity>)


    @Insert
    fun insert(dietEntity: DietEntity)


    @Delete
    fun delete(dietEntity: DietEntity)

    @Query(RoomDietConstants.SELECT_FAVORITES_DIET)
    fun getFavoriteDiets(): Flowable<List<DietEntity>>

    @Query(RoomDietConstants.SELECT_FROM_DIET_EQUAL+"= :remoteId ")
    fun getDietByRemoteId(remoteId:String) : Single<List<DietEntity>>

}