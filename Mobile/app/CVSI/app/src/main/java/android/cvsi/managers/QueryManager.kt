package com.dsdmsa.weather.managers

import android.util.Log
import com.dsdmsa.weather.This
import com.dsdmsa.weather.activityes.MainActivity
import com.dsdmsa.weather.events.Events
import com.dsdmsa.weather.models.forecats.WeatherForecast
import com.dsdmsa.weather.services.WeatherService
import com.google.android.gms.maps.model.LatLng
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.realm.RealmObject
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QueryManager() {

    private val httpClient: OkHttpClient
    private val retrofit: Retrofit
    val weatherService: WeatherService
    val gson: Gson

    init {
        gson = GsonBuilder().setExclusionStrategies(object : ExclusionStrategy {
            override fun shouldSkipField(f: FieldAttributes): Boolean {
                return f.declaringClass == RealmObject::class.java
            }

            override fun shouldSkipClass(clazz: Class<*>): Boolean {
                return false
            }
        }).create()

        httpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(
                        HttpLoggingInterceptor()
                                .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

        retrofit = Retrofit.Builder()
                .baseUrl(MainActivity.API_BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        weatherService = retrofit.create(WeatherService::class.java)
    }

//    fun getWeather(location: LatLng) {
//        Log.d("TAGG","init")
//        weatherService.getWeather(location.longitude, location.latitude).enqueue(object:Callback<WeatherDataModel>{
//            override fun onFailure(call: Call<WeatherDataModel>?, t: Throwable?) {
//                Log.d("TAGG","response no succes")
//            }
//
//            override fun onResponse(call: Call<WeatherDataModel>?, response: Response<WeatherDataModel>?) {
//                Log.d("TAGG","response succes")
//                This.bus.post(Events.WeatherInfo(response?.body()))
//            }
//        })
//
//    }

    fun getForecats(location: LatLng) {
        Log.d("TAGG", "init")
        weatherService.getForecast(location.longitude, location.latitude,"de").enqueue(object : Callback<WeatherForecast> {
            override fun onFailure(call: Call<WeatherForecast>?, t: Throwable?) {
                Log.d("TAGG", "response no succes"+t?.cause)
            }

            override fun onResponse(call: Call<WeatherForecast>?, response: Response<WeatherForecast>?) {
                Log.d("TAGG", "response succes")
                This.bus.post(Events.WeatherInfo(response?.body()))
            }
        })

    }
}
