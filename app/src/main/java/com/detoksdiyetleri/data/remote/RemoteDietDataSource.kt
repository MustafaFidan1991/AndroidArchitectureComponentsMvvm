package com.detoksdiyetleri.data.remote

import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class RemoteDietDataSource @Inject constructor(private val remoteDietService: RemoteDietService) {

    fun getDietsByPageNumber(pageNumber : Int,latestId : String) = remoteDietService.getDiets(pageNumber,latestId)

}