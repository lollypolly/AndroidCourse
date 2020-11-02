package com.example.basicproject.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.basicproject.R
import kotlinx.android.extensions.LayoutContainer

class CatHolder(
    override val containerView: View,
    private val likeClick: (Cat, Int) -> Unit,
    private val itemClick: (Cat) -> Unit

) : RecyclerView.ViewHolder(containerView), LayoutContainer {


    private val kindCat: TextView = itemView.findViewById(R.id.text_list)
    private val photoCat: ImageView = itemView.findViewById(R.id.image_list)
    private val likeCat: ImageView = itemView.findViewById(R.id.ic_like_list)
    private val deleteCat: ImageView = itemView.findViewById(R.id.ic_delete_list)
    private val commentCat: TextView = itemView.findViewById(R.id.comment_list)


    private var cat: Cat? = null

    init {
        itemView.setOnClickListener {
            cat?.also(itemClick)
        }
    }


    fun bind(cat: Cat) {
        with(cat) {
            kindCat.text = kind
            photoCat.setImageResource(photo)
            likeCat.setImageResource(likes)
            commentCat.text = comment
            deleteCat.setImageResource(delete)
        }
        likeCat.setOnClickListener {
            likeClick(cat, layoutPosition)
        }
        itemView.setOnClickListener {
            itemClick(cat)
        }

    }

    companion object {

        fun create(parent: ViewGroup, likeClick: (Cat, Int) -> Unit, itemClick: (Cat) -> Unit) =
            CatHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.second_recycler_frag, parent, false),
                likeClick,
                itemClick
            )
    }
}