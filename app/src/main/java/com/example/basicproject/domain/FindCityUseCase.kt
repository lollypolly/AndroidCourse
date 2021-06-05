package com.example.basicproject.domain

import com.example.basicproject.data.model.entity.WeatherEntity
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class FindCityUseCase(
        private val weatherRepository: WeatherRepository,
        private val context: CoroutineContext
) {

    suspend fun findWeatherInCity(weatherEntity: WeatherEntity) =
            withContext(context) {
                weatherRepository.saveBase(weatherEntity)
            }

}