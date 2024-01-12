package com.example.weatherchecker.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [entityClass::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun itemDao():CityDao


}