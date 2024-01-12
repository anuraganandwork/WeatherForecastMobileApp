package com.example.weatherchecker.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cityClass: entityClass)

    @Delete
    suspend fun delete(cityClass: entityClass)

    @Query("SELECT * from favouriteCity ORDER BY cityName ASC")
    fun getTheList(): Flow<List<entityClass>>
}