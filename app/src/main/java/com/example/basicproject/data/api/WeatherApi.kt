package com.example.basicproject.data.api

import com.example.basicproject.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather?units=metric&lang=ru")
    suspend fun getWeather(@Query("id") cityId: Int) : WeatherResponse

    @GET("weather?units=metric&lang=ru")
    suspend fun getWeather(@Query("q") cityName: String) : WeatherResponse

    @GET("find?units=metric&lang=ru&cnt=15")
    suspend fun getSquareWeather(@Query("lat") lat: Double, @Query("lon") lon: Double) : WeatherResponse

}
