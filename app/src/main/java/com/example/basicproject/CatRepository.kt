package com.example.basicproject

object CatRepository {

    fun getBooks() = arrayListOf(
        Cat("1", "Британская кошка"),
        Cat("2", "Кошка Рэгдолл"),
        Cat("3", "Тайская кошка"),
        Cat("4", "Абиссинская кошка"),
        Cat("5", "Персидская кошка")
    )
}
