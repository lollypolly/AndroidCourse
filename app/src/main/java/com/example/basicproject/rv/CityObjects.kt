package com.example.basicproject.rv

import com.example.basicproject.WeatherResponse


object CityObjects {
    var cityObjects = ArrayList<WeatherResponse>()

    fun addItem(city: WeatherResponse) = cityObjects.add(city)

    fun clear() = cityObjects.clear()
}