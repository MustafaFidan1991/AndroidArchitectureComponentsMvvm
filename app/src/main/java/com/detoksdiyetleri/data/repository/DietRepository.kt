package com.detoksdiyetleri.data.repository

import android.arch.lifecycle.LiveData
import com.detoksdiyetleri.data.remote.RemoteDietDataSource
import com.detoksdiyetleri.data.room.DietEntity
import com.detoksdiyetleri.data.room.RoomDietDataSource
import com.detoksdiyetleri.model.Diet
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class DietRepository@Inject constructor(
        private val roomDietDataSource: RoomDietDataSource,
        private val remoteDietDataSource: RemoteDietDataSource
) : IDietRepository {

    override fun insert(dietEntity: DietEntity) {
        roomDietDataSource.dietDao().insert(dietEntity)
    }

    override fun delete(dietEntity: DietEntity) {
        roomDietDataSource.dietDao().delete(dietEntity)
    }

    override fun setDietToFavorites() {

    }

    override fun getFavoritesDiets(): Flowable<List<DietEntity>> {
        return roomDietDataSource.dietDao().getFavoriteDiets()
    }

    override fun getDietsByPage(pageNumber : Int, latestId : String): Query {
        return remoteDietDataSource.getDietsByPageNumber(pageNumber,latestId)
    }

    override fun getDietByRemoteId(remoteId: String) : Single<List<DietEntity>> {
        return roomDietDataSource.dietDao().getDietByRemoteId(remoteId)
    }


}