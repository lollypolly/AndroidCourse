package com.example.basicproject.rv

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basicproject.R
import com.example.basicproject.WeatherResponse


import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.city_pattern.*

class CityHolder(
    override val containerView: View,
    private val action: (Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    @SuppressLint("SetTextI18n")
    fun bind(city: WeatherResponse) {
        with(city) {
            name_city.text = name
            temp_rv.text = main.tempr.toInt().toString() + '°'
           // val t = main.tempr.toInt()
            if (main.tempr.toInt() < -25) temp_rv.setTextColor(Color.parseColor("#7593FF"))
            if (main.tempr.toInt() >= -25 && main.tempr.toInt() <= -15) temp_rv.setTextColor(Color.parseColor("#89FCE7"))
            if (main.tempr.toInt() >= -14 && main.tempr.toInt() <= -5) temp_rv.setTextColor(Color.parseColor("#FFD597"))
            if (main.tempr.toInt() >= -4 && main.tempr.toInt() <= 5) temp_rv.setTextColor(Color.parseColor("#FFA451"))
            if (main.tempr.toInt() >= 6) temp_rv.setTextColor(Color.parseColor("#FF8080"))
            itemView.setOnClickListener { action(id) }
        }
    }

    fun updateFromBundle(bundle: Bundle) {
        if (bundle.containsKey("ARG_NAME")) {
            bundle.getString("ARG_NAME").also {
                name_city.text = it
            }
        }
        if (bundle.containsKey("ARG_INFO")) {
            bundle.getString("ARG_INFO").also {
                temp_rv.text = it
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup, action: (Int) -> Unit): CityHolder =
            CityHolder(LayoutInflater.from(parent.context).inflate(R.layout.city_pattern, parent, false), action)
    }


}