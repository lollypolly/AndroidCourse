package com.example.basicproject.cards

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.basicproject.R

class ViewPagerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var Image = itemView.findViewById<ImageView>(R.id.image)

    fun bind(image: Int) {
        Image.setImageResource(image)
    }
}