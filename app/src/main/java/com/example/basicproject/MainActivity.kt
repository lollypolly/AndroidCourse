package com.example.basicproject

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        call.setOnClickListener() {
            val intent = Intent(
                Intent.ACTION_VIEW, Uri.parse("https://www.stackoverflow.com/")
            )
            val chooser = Intent.createChooser(intent, "Choose Your Browser")

            if (chooser.resolveActivity(packageManager) != null) {
                startActivityForResult(chooser, 1)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_CANCELED) {
            Log.d("a", "" + requestCode + resultCode)
            Toast.makeText(this, "Пользователь нажал на браузер", Toast.LENGTH_LONG).show()
        }
        val extras = data?.extras
        if (extras != null && extras.getInt("CHEEEK") == 1) {
            Toast.makeText(this, "Пользователь нажал на новый экран", Toast.LENGTH_LONG).show()
        }
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}
