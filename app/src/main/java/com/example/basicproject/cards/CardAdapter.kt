package com.example.basicproject.cards

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basicproject.R

class CardAdapter(
    var cardList: ArrayList<Card>
) : RecyclerView.Adapter<CardHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder =
        CardHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        )

    override fun getItemCount(): Int = cardList.size

    override fun onBindViewHolder(holder: CardHolder, position: Int) =
        holder.bind(cardList[position])

}