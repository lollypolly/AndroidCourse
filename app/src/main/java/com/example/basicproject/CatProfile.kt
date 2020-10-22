package com.example.basicproject

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CatProfile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cat_profile)

        val kindCat = findViewById<TextView>(R.id.tv_cat_kind_b)
        val countryCat = findViewById<TextView>(R.id.tv_country_cat_b)
        val id = intent.getSerializableExtra("id")
        val photoCat = findViewById<ImageView>(R.id.img_cat_b)
        val contentCat = findViewById<TextView>(R.id.tv_content_cat)

        val CatRep: ArrayList<Cat> = CatRepository.getCats()
        for (i in 0 until CatRep.size) {
            if (CatRep[i].id == id) {
                kindCat.text = CatRep[i].kind
                countryCat.text = CatRep[i].country
                contentCat.text = CatRep[i].content
                photoCat.setImageResource(CatRep[i].photo)
            }
        }

    }
}