package com.example.basicproject

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiCity {

    private val apiInterceptor = Interceptor { chain ->
        val original = chain.request()
        original.url().newBuilder()
                .build()
                .let {
                    chain.proceed(
                            original.newBuilder().url("https://raw.githubusercontent.com").build()
                    )
                }
    }

    private val client by lazy {
        OkHttpClient.Builder()
                .addInterceptor(LoggingInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
                .client(client)
                .baseUrl("https://raw.githubusercontent.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    val cityApi: CityApi by lazy {
        retrofit.create(CityApi::class.java)
    }
}
