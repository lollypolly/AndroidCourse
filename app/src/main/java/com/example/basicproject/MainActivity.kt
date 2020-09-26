package com.example.basicproject

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nickname.setText(getIntent().getStringExtra("name"))
        editText.setText("About me:"+"\n"+getIntent().getStringExtra("email")+"\n"+getIntent().getStringExtra("age")+" y.o")
        aboutme.setText("About me:"+"\n"+getIntent().getStringExtra("email")+"\n"+getIntent().getStringExtra("age")+" y.o")
        button2.setOnClickListener {
            if (editText.visibility == View.INVISIBLE) {
                aboutme.visibility=View.INVISIBLE
                editText.visibility = View.VISIBLE
                button2.text = "Сохранить"

            }
            else if (editText.visibility == View.VISIBLE) {
                aboutme.visibility = View.VISIBLE
                editText.visibility = View.INVISIBLE
                aboutme.setText(editText.text)
                button2.text = "Редактировать..."
            }

        }

        }



    }


