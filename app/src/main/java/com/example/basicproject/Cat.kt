package com.example.basicproject

//base class of Cat
data class Cat (
    val id : Int,
    val kind : String,
    val country : String,
    val photo : Int,
    val content : String,
    var likes : Int
)