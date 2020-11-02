package com.example.basicproject.list

data class Cat(
    val id: Int,
    val photo: Int,
    val kind: String,
    val comment: String,
    var likes: Int,
    var delete: Int
) {
}