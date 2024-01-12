package com.example.weatherchecker.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favouriteCity")
data class entityClass (



//    @ColumnInfo("id")
//    var id: Int =0,

    @PrimaryKey
    @ColumnInfo("cityName")
    var favouriteCity: String

//    @ColumnInfo("unitUsed")
//     var unitUsing: String = "Celcius"
)

