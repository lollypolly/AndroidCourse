package com.example.basicproject.data

import android.content.Context
import android.location.Location
import com.example.basicproject.data.api.WeatherApi
import com.example.basicproject.data.model.AppDatabase
import com.example.basicproject.data.model.entity.WeatherEntity
import com.example.basicproject.domain.WeatherRepository


class WeatherRepositoryImpl(private val weatherApi: WeatherApi, context: Context) :
    WeatherRepository {
    private var db: AppDatabase = AppDatabase(context)

    override suspend fun getBase(): WeatherEntity? = db.weatherDao().getBaseCity()

    override suspend fun saveBase(weatherEntity: WeatherEntity) {
        db.weatherDao().deleteBase()
        db.weatherDao().saveBase(weatherEntity)
    }

    override suspend fun getLocation(): Location? {
        return null
    }

    override suspend fun saveAroundLocation(list: List<WeatherEntity>): List<WeatherEntity> {
        db.weatherDao().clearTableForAround()
        db.weatherDao().saveAroundList(list)
        return list
    }

    override suspend fun getAllTable(): List<WeatherEntity> = db.weatherDao().getAllTable()

    override suspend fun getAroundCity(): List<WeatherEntity> = db.weatherDao().getAroundCity()

    override suspend fun deleteAround() = db.weatherDao().clearTableForAround()

    override suspend fun getCityById(id: Int): WeatherEntity = db.weatherDao().getCityById(id)
}
