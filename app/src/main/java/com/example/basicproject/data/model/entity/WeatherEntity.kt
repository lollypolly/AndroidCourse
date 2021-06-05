package com.example.basicproject.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Weather")
data class WeatherEntity(
    @ColumnInfo(name = "base_city")
    var base: Int,
    @ColumnInfo(name = "name_of_city")
    var name: String,
    @PrimaryKey
    @ColumnInfo(name = "id_of_city")
    var id: Int,
    @ColumnInfo(name = "lat")
    var lat: Double,
    @ColumnInfo(name = "lon")
    var lon: Double,
    @ColumnInfo(name = "temp")
    var temp: Int,
    @ColumnInfo(name = "descr")
    var descr: String,
    @ColumnInfo(name = "feel")
    var feel: String,
    @ColumnInfo(name = "speed")
    var speed: String,
    @ColumnInfo(name = "direction")
    var direction: String
)// {
//    constructor(base: Int, name: String, id: Int, lat: Double, lon: Double, temp: Int): this(base, name, id, lat, lon, temp)
//}