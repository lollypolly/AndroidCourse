package com.example.basicproject.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.basicproject.R
import kotlinx.android.synthetic.main.second_recycler_frag.*

class ListFragment : Fragment() {

    private var adapter: CatAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.second_recycler_frag, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = CatAdapter(
            Cats.cats,
            { cat: Cat, i: Int ->
                if (cat.likes == R.drawable.ic_like) {
                    cat.likes = R.drawable.ic_act
                } else {
                    cat.likes = R.drawable.ic_like
                }
            },
            {
            }
        )
        recycler_cats.adapter = adapter

        swipe.setOnRefreshListener {
            swipe.isRefreshing = false
        }
    }

}








