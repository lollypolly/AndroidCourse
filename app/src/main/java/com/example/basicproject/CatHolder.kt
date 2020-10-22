import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.basicproject.Cat
import com.example.basicproject.R
import kotlinx.android.extensions.LayoutContainer

class CatHolder(
    override val containerView: View,
    private val likeClick: (Cat, Int) -> Unit,
    private val itemClick: (Cat) -> Unit

) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private val kindCat = itemView.findViewById<TextView>(R.id.tv_cat_kind)
    private val countryCat = itemView.findViewById<TextView>(R.id.tv_cat_country)
    private val photoCat = itemView.findViewById<ImageView>(R.id.img_cat)
    private val like = itemView.findViewById<ImageView>(R.id.like)


    private var cat: Cat? = null

    init {
        itemView.setOnClickListener {
            cat?.also(itemClick)
        }
    }


    fun bind(cat: Cat) {
        with(cat) {
            kindCat.text = kind
            countryCat.text = country
            photoCat.setImageResource(photo)
            like.setImageResource(likes)
        }
        like.setOnClickListener {
            likeClick(cat, layoutPosition)
        }
        itemView.setOnClickListener {
            itemClick(cat)
        }

    }

    companion object {

        fun create(parent: ViewGroup, likeClick: (Cat, Int) -> Unit, itemClick: (Cat) -> Unit) =
            CatHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_cat, parent, false),
                likeClick,
                itemClick
            )
    }
} 