package com.dsdmsa.weather.services

import com.dsdmsa.weather.models.forecats.WeatherForecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY_FREE_WEATHER = "5265e1dadec7a8355c402b612535f67d"
const val API_KEY_FLIRK = "45771fc1931c864bf0a6b0188e54ee15"
const val SECTER_FLIRK = "3735c5e0683e184c"

interface WeatherService {
    @GET("forecast?APPID=$API_KEY_FREE_WEATHER")
    fun getForecast(
            @Query("lon") lon: Double,
            @Query("lat") lat: Double,
            @Query("lang") lang:String
    ): Call<WeatherForecast>
}

