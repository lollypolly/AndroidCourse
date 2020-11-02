package com.example.basicproject.cards

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.basicproject.R

class CardHolder(
    containerView: View
) : RecyclerView.ViewHolder(containerView) {

    var userPhoto: ImageView = itemView.findViewById(R.id.photo)
    var username: TextView = itemView.findViewById(R.id.username)
    var viewPager: ViewPager2 = itemView.findViewById(R.id.view_pager)
    var username2: TextView = itemView.findViewById(R.id.username_2)
    var comment: TextView = itemView.findViewById(R.id.comment)

    fun bind(card: Card) {
        userPhoto.setImageResource(card.userPhoto)
        username.text = card.username
        comment.text = card.comment
        username2.text = card.username
        viewPager.adapter = card.viewPager

    }

}