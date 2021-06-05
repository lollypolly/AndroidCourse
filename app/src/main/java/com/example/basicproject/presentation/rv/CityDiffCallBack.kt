package com.example.basicproject.presentation.rv.recycler

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.example.basicproject.WeatherResponse
import com.example.basicproject.data.model.entity.WeatherEntity

class CityDiffCallback(
    private var oldList: List<WeatherEntity>,
    private var newList: List<WeatherEntity>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val bundle = Bundle().apply {
            if (oldList[oldItemPosition].name != (newList[newItemPosition].name)) {
                putString("ARG_NAME", newList[newItemPosition].name)
            }
            if (oldList[oldItemPosition].temp != (newList[newItemPosition].temp)) {
                putString("ARG_INFO", newList[newItemPosition].temp.toString())
            }

        }
        return if (bundle.isEmpty) null else bundle
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}

