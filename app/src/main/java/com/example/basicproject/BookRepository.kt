package com.example.basicproject

object BookRepository {

    fun getBooks() = arrayListOf(
        Book("Gena", "Cheburashka"),
        Book("Cheburashka", "Gena"),
        Book("Cheburashka", "Gena"),
        Book("Cheburashka", "Gena"),
        Book("Gennadiy", "Crocodile")
    )
}
