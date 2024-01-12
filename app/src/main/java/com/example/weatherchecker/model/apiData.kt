package com.example.weatherchecker.model

data class apiData(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<weatherObj>,
    val message: Double
)