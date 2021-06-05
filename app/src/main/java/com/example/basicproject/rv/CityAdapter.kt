package com.example.basicproject.rv

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.basicproject.WeatherResponse


class CityAdapter(
    private var list: List<WeatherResponse>,
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

    fun updateData(newList: List<WeatherResponse>) {
        val callback = CityDiffCallback(list, newList)
        val diffResult = DiffUtil.calculateDiff(callback, true)
        diffResult.dispatchUpdatesTo(this)
        list = mutableListOf<WeatherResponse>().apply { addAll(newList) }
    }
}