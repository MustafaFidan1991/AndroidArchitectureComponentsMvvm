package com.detoksdiyetleri.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(
        entities = arrayOf(DietEntity::class),
        version = 1)
abstract class RoomDietDataSource : RoomDatabase(){


    abstract fun dietDao(): RoomDietDao

    companion object {

        fun buildPersistentDiet(context: Context): RoomDietDataSource = Room.databaseBuilder(
                context.applicationContext,
                RoomDietDataSource::class.java,
                RoomDietConstants.DATABASE_DIET
        ).build()
    }

}