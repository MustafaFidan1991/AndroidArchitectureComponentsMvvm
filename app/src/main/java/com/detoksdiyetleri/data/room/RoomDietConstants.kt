package com.detoksdiyetleri.data.room

class RoomDietConstants {
    companion object {

        const val DATABASE_DIET = "diet.db"

        const val TABLE_DIETS = "diets"

        private const val SELECT_FROM = "SELECT * FROM "

        const val SELECT_FROM_DIET_EQUAL = SELECT_FROM+ TABLE_DIETS+" WHERE remote_id="

        const val SELECT_FAVORITES_DIET = SELECT_FROM + TABLE_DIETS

    }
}