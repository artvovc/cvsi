package com.dsdmsa.weather.controllers

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager


class CacheController(context: Context) {

    companion object {
        val TAG = "CacheController"

        val PREFIX = "com.dsdmsa.weather"
        val CACHE = "${PREFIX}CHACHE"
        val FIRST_TIME = "${PREFIX}FIRST_TIME"
        val MAX_MIN_TEMP = "${PREFIX}MAX_MIN_TEMP"
        val SUR_RISE_SET = "${PREFIX}SUR_RISE_SET"
        val WEATHER = "${PREFIX}WEATHER"

    }

    private val prefs: SharedPreferences
    private val appPrefs: SharedPreferences

    init {
        prefs = context.getSharedPreferences(CACHE, Context.MODE_PRIVATE)
        appPrefs = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun setFirstTime(firstTime: Boolean) {
        prefs.edit().putBoolean(FIRST_TIME, firstTime).apply()
    }

    fun getFirstTime(): Boolean {
        return prefs.getBoolean(FIRST_TIME, true)
    }

    fun setMAX_MIN_TEMP(firstTime: Boolean) {
        prefs.edit().putBoolean(MAX_MIN_TEMP, firstTime).apply()
    }

    fun getMAX_MIN_TEMP(): Boolean {
        return prefs.getBoolean(MAX_MIN_TEMP, true)
    }

    fun setSUR_RISE_SET(firstTime: Boolean) {
        prefs.edit().putBoolean(SUR_RISE_SET, firstTime).apply()
    }

    fun getSUR_RISE_SET(): Boolean {
        return prefs.getBoolean(SUR_RISE_SET, true)
    }

//    fun saveWeather(weatherData: CachedWeatherDataModel?) {
//        try {
//            prefs.edit().putString(WEATHER, JsonMarshaller.toJson(weatherData)).apply()
//        } catch (e: Exception) {
//            Log.e(TAG, e.message)
//        }
//    }
//
//    val weather: CachedWeatherDataModel?
//        get() {
//            val jsonData = prefs.getString(WEATHER, null)
//            if (jsonData != null) {
//                try {
//                    return JsonMarshaller.fromJson(CachedWeatherDataModel::class.java, jsonData)
//                } catch (e: Exception) {
//                    prefs.edit().remove(WEATHER).apply()
//                    return null
//                }
//            }
//            return null
//        }
}