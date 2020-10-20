package com.example.basicproject

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var adapter: CatAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = СatAdapter(
            CatRepository.getBooks()
        ) {
            Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show()
        }
        rv_book.adapter = adapter
        rv_book.addItemDecoration(SpaceItemDecoration(this))

            swipe.setOnRefreshListener {
            adapter?.updateDataSource(arrayListOf(Cat("Мур", "Привяу")))
            swipe.isRefreshing = false
        }
    }
}