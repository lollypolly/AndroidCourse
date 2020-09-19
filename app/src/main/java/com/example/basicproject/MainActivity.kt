package com.example.basicproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button2.setOnClickListener {
            if (editText.visibility == View.INVISIBLE) {
                editText.visibility = View.VISIBLE
                button2.setText("Сохранить")
            }
                else if (editText.visibility == View.VISIBLE) {
                editText.visibility = View.INVISIBLE
                button2.setText("Редактировать...")
            }


            }


        }
    }
