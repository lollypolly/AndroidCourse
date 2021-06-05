package com.example.basicproject.domain

import android.location.Location
import com.example.basicproject.data.model.entity.WeatherEntity


interface WeatherRepository {

    suspend fun getBase(): WeatherEntity?

    suspend fun getAllTable(): List<WeatherEntity>

    suspend fun saveBase(weatherEntity: WeatherEntity)

    suspend fun getLocation(): Location?

    suspend fun saveAroundLocation(list: List<WeatherEntity>): List<WeatherEntity>

    suspend fun getAroundCity(): List<WeatherEntity>

    suspend fun deleteAround()

    suspend fun getCityById(id: Int): WeatherEntity

}