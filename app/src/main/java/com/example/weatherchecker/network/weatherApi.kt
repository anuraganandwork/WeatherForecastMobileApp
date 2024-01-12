package com.example.weatherchecker.network

import com.example.weatherchecker.model.apiData
import com.example.weatherchecker.utility.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface weatherApi {

    @GET("data/2.5/forecast/daily?")
    suspend fun getWeather(
        @Query("q") query : String,
        @Query("units") units : String = "imperial",
        @Query("appid") appid : String = Constants.API_Key

    ): apiData


}