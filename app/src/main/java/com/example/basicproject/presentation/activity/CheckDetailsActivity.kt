package com.example.basicproject.presentation.activity


import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.example.basicproject.R

import com.example.basicproject.R.layout.activity_detail_city
import com.example.basicproject.data.ConnectionValidator
import com.example.basicproject.data.WeatherRepositoryImpl
import com.example.basicproject.data.api.ApiFactory
import com.example.basicproject.domain.WeatherRepository
import kotlinx.android.synthetic.main.activity_detail_city.*

import kotlinx.coroutines.launch


class CheckDetailsActivity : AppCompatActivity() {
    lateinit var repo: WeatherRepository

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_city)
        repo = WeatherRepositoryImpl(ApiFactory.weatherApi, this)
        var isNetworkEnabled = ConnectionValidator().verifyAvailableNetwork(this)
        if (isNetworkEnabled) {
            if (intent != null) {
                temp_view.text = intent.getStringExtra("TEMP") + '°'
                description.text = intent.getStringExtra("DESCR")
                city_view.text = intent.getStringExtra("NAME")
                wind_view.text = intent.getStringExtra("WIND_SPEED") + " м/с, " + intent.getStringExtra("WIND_DEG")
                feel_view.text = intent.getStringExtra("FEEL") + '°'
            }
        } else {
            lifecycleScope.launch {
                var idCity: Int = -1
                if (intent != null) {
                    idCity = intent.getIntExtra("ID", 1)
                }
                var myCity = repo.getCityById(idCity)
                if (myCity != null) {
                    with(myCity) {
                        temp_view.text = temp.toString() + '°'
                        description.text = descr
                        city_view.text = name
                        wind_view.text = speed + " м/с, " + direction
                        feel_view.text = feel + '°'
                    }
                }
            }
        }
    }

}