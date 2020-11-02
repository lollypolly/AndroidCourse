package com.example.basicproject.cards

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basicproject.R

class ViewPagerAdapter(
    val images: ArrayList<Int>
) : RecyclerView.Adapter<ViewPagerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder =
        ViewPagerHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.viewpager_item, parent, false)
        )


    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) = holder.itemView.run {
        holder.bind(images[position])
    }
}