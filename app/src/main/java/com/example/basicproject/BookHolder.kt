package com.example.basicproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_book.view.*

class BookHolder(
    override val containerView: View,
    private val itemClick: (Book) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private var book: Book? = null

    init {
        itemView.setOnClickListener {
            book?.also(itemClick)
        }
    }

    fun bind(book: Book) {
        this.book = book
        with(book) {
            itemView.tv_name.text = name
            itemView.tv_desc.text = author
        }
    }

    companion object {

        fun create(parent: ViewGroup, itemClick: (Book) -> Unit): BookHolder =
            BookHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false),
                itemClick
            )

    }
}