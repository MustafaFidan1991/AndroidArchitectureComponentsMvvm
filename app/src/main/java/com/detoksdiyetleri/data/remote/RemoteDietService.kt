package com.detoksdiyetleri.data.remote

import com.google.firebase.database.*
import javax.inject.Inject

class RemoteDietService @Inject constructor(private val firebaseDatabase: FirebaseDatabase){

    fun getDiets(pageNumber : Int,latestId : String) : Query{
        return firebaseDatabase.
                getReference(RemoteConstants.DIETS_REFERENCE).
                endAt(latestId,latestId).
                limitToLast(pageNumber)
    }

}