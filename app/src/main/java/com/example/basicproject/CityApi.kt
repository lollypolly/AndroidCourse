package com.example.basicproject

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

interface CityApi {

    @GET("aZolo77/citiesBase/master/cities.json")
    suspend fun getId() : IdCityX

    data class IdCityX(
        @SerializedName("city")
        var city: List<CityX>
    )

    data class CityX(
        @SerializedName("city_id")
        var cityId: String,
        @SerializedName("country_id")
        var countryId: String,
        @SerializedName("name")
        var name: String,
        @SerializedName("region_id")
        var regionId: String
    )

}