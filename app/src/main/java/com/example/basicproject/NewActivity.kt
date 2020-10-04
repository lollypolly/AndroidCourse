package com.example.basicproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_new.*

class NewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
        exit.setOnClickListener(){
            Toast.makeText(this, "Пользователь нажал на новый экран", Toast.LENGTH_LONG).show()
            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra("CHEEEK", 1)
            })
            finish()
        }

    }
}