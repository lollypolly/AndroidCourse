package com.example.basicproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = CatAdapter(
            CatRepository.getCats(),
            { cat: Cat, i: Int ->
                if (cat.likes == R.drawable.ic_like) {
                    cat.likes = R.drawable.ic_like_act
                } else {
                    cat.likes = R.drawable.ic_like
                }
                recyclerView.adapter?.notifyItemChanged(i)

            },
            {
                var cat: Cat = it
                var intent = Intent(this, CatProfile::class.java)
                intent.putExtra("id", cat.id)
                startActivity(intent)

            }
        )

        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        swipe.setOnRefreshListener {
            swipe.isRefreshing = false
        }
    }
}

