package com.example.basicproject.presentation.rv

import CityHolder
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.basicproject.data.model.entity.WeatherEntity
import com.example.basicproject.presentation.rv.recycler.CityDiffCallback

class CityAdapter(
    private var list: List<WeatherEntity>,
    private val action: (Int) -> Unit
) : RecyclerView.Adapter<CityHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder =
        CityHolder.create(parent, action)

    override fun onBindViewHolder(holder: CityHolder, position: Int) =
        holder.bind(list[position])

    override fun onBindViewHolder(holder: CityHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) super.onBindViewHolder(holder, position, payloads)
        else {
            (payloads[0] as? Bundle)?.also {
                holder.updateFromBundle(it)
            } ?: run { super.onBindViewHolder(holder, position, payloads) }
        }
    }

    override fun getItemCount(): Int = list.size

    fun updateData(newList: List<WeatherEntity>) {
        val callback = CityDiffCallback(list, newList)
        val diffResult = DiffUtil.calculateDiff(callback, true)
        diffResult.dispatchUpdatesTo(this)
        list = mutableListOf<WeatherEntity>().apply { addAll(newList) }
    }
}