package com.example.basicproject

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(
    private var list: List<Book>,
    private val itemClick: (Book) -> Unit
) : RecyclerView.Adapter<BookHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder =
        BookHolder.create(parent, itemClick)

    override fun onBindViewHolder(holder: BookHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    fun updateDataSource(newList: List<Book>) {
        list = newList
        notifyDataSetChanged()
    }
}