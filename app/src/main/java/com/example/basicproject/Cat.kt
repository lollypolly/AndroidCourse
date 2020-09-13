package com.example.basicproject

class Cat(override val name:String,
          override val weight:Int,
          override val size:String) :Animal(name,weight,size),Jumpble {


    override fun Voice() {
       println("Мяу")
    }

    override fun jump() {
        println("ДА-ДА, УМЕЮ")
    }
}