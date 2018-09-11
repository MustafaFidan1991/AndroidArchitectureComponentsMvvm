package com.detoksdiyetleri.data.room

import android.arch.persistence.room.*
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


    @Query(RoomDietConstants.DELETE_BY_DIET+"= :remoteId ")
    fun delete(remoteId: String)

    @Query(RoomDietConstants.SELECT_FAVORITES_DIET)
    fun getFavoriteDiets(): Flowable<List<DietEntity>>

    @Query(RoomDietConstants.SELECT_FROM_DIET_EQUAL+"= :remoteId ")
    fun getDietByRemoteId(remoteId:String) : Single<List<DietEntity>>

}