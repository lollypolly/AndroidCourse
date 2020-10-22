package com.example.basicproject

import CatHolder
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CatAdapter(
    private val list: List<Cat>,
    private val likeClick: (Cat, Int) -> Unit,
    private val itemClick: (Cat) -> Unit
) : RecyclerView.Adapter<CatHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatHolder =
        CatHolder.create(parent, likeClick, itemClick)

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CatHolder, position: Int) = holder.bind(list[position])
}