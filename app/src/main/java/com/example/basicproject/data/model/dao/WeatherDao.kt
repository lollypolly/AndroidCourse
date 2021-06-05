package com.example.basicproject.data.model.dao

import androidx.room.*
import com.example.basicproject.data.model.entity.WeatherEntity


@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBase(weather: WeatherEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAroundList(list: List<WeatherEntity>)

    @Query("select * from Weather where base_city = 1")
    suspend fun getBaseCity(): WeatherEntity?

    @Query("select * from Weather where base_city = 0")
    suspend fun getAroundCity(): List<WeatherEntity>

    @Query("select * from Weather")
    suspend fun getAllTable(): List<WeatherEntity>

    @Query("delete from Weather")
    suspend fun clearTable()

    @Query("delete from Weather where base_city = 0")
    suspend fun clearTableForAround()

    @Query("delete from Weather where base_city = 1")
    suspend fun deleteBase()

    @Query("select * from Weather where id_of_city = :id")
    suspend fun getCityById(id: Int): WeatherEntity

}