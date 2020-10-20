package com.example.basicproject


    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.recyclerview.widget.RecyclerView
    import kotlinx.android.extensions.LayoutContainer
    import kotlinx.android.synthetic.main.recycle_item.*

    class CatHolder(
        override val containerView: View,
        private val itemClick: (Cat) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        private var cat: Cat? = null

        init {
            itemView.setOnClickListener {
                cat?.also(itemClick)
            }
        }

        fun bind(cat: Cat) {
            this.cat = cat
            with(cat) {
                tv_name.text = name
                tv_desc.text = id
            }
        }

        companion object {

            fun create(parent: ViewGroup, itemClick: (Cat) -> Unit): CatHolder =
                CatHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.recycle_item, parent, false),
                    itemClick
                )

        }
    }