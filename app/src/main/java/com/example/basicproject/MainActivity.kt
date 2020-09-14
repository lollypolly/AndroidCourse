package com.example.basicproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var animal = Animal("Barsik", 1, "маленький")
        animal.info()

        var animal2 = Cat("Kit", 4, "большой")
        animal2.info()
        animal2.jump()
    }
}