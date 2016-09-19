package com.dsdmsa.weather.events

import com.dsdmsa.weather.models.forecats.WeatherForecast

object Events {
    class Behavior(behavior: Int) {
        var value = behavior
    }

    class WeatherInfo(weather: WeatherForecast?) {
        var info = weather
    }

    class NotifyAdapter(){}
}
