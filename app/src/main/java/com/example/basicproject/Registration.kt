package com.example.basicproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_registration.*


class Registration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        start.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)

            intent.putExtra("name", et_name.text.toString())
            intent.putExtra("age", et_age.text.toString())
            intent.putExtra("email", et_email.text.toString())

            startActivity(intent)
        }
    }

}



