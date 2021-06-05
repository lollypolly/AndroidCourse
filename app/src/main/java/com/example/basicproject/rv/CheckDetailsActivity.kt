package com.example.basicproject.rv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.basicproject.R

import kotlinx.android.synthetic.main.activity_detail_city.*


class CheckDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_city)
        if (intent != null) {
            temp_view.text = intent.getIntExtra("TEMP", 0).toString() + '°'
            temp_view.text = intent.getStringExtra("FEEL") + '°'
            description.text = intent.getStringExtra("DESCR")
            city_view.text = intent.getStringExtra("NAME")
            wind_view.text = intent.getStringExtra("WIND_SPEED") + " м/с, " + intent.getStringExtra("WIND_DEG")
            feel_view.text = intent.getStringExtra("FEEL") + '°'
        }
    }
}