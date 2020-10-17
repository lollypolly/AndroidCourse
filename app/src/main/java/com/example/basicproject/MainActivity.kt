package com.example.basicproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toast.makeText
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imgOne:ImageView = findViewById(R.id.img_1)
        val imgTwo:ImageView = findViewById(R.id.img_2)
        val imgThree:ImageView = findViewById(R.id.img_3)
        val imgFour:ImageView = findViewById(R.id.img_4)
        val imgFive:ImageView = findViewById(R.id.img_5)

        var img : ImageView? = null


        fun colorChahge(){
            ic_one.setBackgroundColor(resources.getColor(R.color.colorWhite))
            ic_two.setBackgroundColor(resources.getColor(R.color.colorWhite))
            ic_three.setBackgroundColor(resources.getColor(R.color.colorWhite))
            ic_four.setBackgroundColor(resources.getColor(R.color.colorWhite))
            ic_five.setBackgroundColor(resources.getColor(R.color.colorWhite))
        }

        ic_one.setOnClickListener{
            colorChahge()
            it.isSelected = true
            it.setBackgroundColor(resources.getColor(R.color.colorAccent))
            text.visibility = View.GONE
            img?.visibility  = View.GONE
            imgOne.visibility = View.VISIBLE
            img = imgOne

       
        }

        ic_two.setOnClickListener{
            colorChahge()
            it.isSelected = true
            text.visibility = View.GONE
            it.setBackgroundColor(resources.getColor(R.color.colorAccent))
            img?.visibility  = View.GONE
            imgTwo.visibility = View.VISIBLE
            img = imgTwo
           
        }

        ic_three.setOnClickListener{
            colorChahge()
            it.isSelected = true
            text.visibility = View.GONE
            it.setBackgroundColor(resources.getColor(R.color.colorAccent))
            img?.visibility  = View.GONE
            imgThree.visibility = View.VISIBLE
            img = imgThree
        }

        ic_four.setOnClickListener{
            colorChahge()
            it.isSelected = true
            text.visibility = View.GONE
            it.setBackgroundColor(resources.getColor(R.color.colorAccent))
            img?.visibility  = View.GONE
            imgFour.visibility = View.VISIBLE
            img = imgFour
        }

        ic_five.setOnClickListener{
            colorChahge()
            it.isSelected = true
            text.visibility = View.GONE
            it.setBackgroundColor(resources.getColor(R.color.colorAccent))
            img?.visibility  = View.GONE
            imgFive.visibility = View.VISIBLE
            img = imgFive
        }

        back.setOnClickListener{
            text.visibility = View.VISIBLE
            img?.visibility = View.GONE
            var snackbar = Snackbar.make(it,"Выберите картиночку", Snackbar.LENGTH_SHORT)
            val sbView:View = snackbar.getView()
            sbView.setBackgroundColor(resources.getColor(R.color.colorAccent))
            snackbar.show()
        }
    }
}

