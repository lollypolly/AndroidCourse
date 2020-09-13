package com.example.basicproject


open class Animal (open val name:String, open val weight:Int, open val size:String) {
    open fun info() {
        println(name + "" + weight + "" + size)
    }

    open fun Voice() {
        println("тут должен быть звук животного")
    }

}