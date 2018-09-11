package com.detoksdiyetleri.data.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = RoomDietConstants.TABLE_DIETS)
 class DietEntity(){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var uid: Int = 0

    @ColumnInfo(name = "remote_id")
    var remoteId : String= ""

    var title : String= ""

    var content:String= ""

    @ColumnInfo(name = "img_url")
    var dietImgUrl:String= ""

   @ColumnInfo(name = "created_date")
    var createdDate : String= ""


}