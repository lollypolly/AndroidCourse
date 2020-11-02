package com.example.basicproject.cards

data class Card(
    val username: String,
    val userPhoto: Int,
    val comment: String,
    val viewPager: ViewPagerAdapter
) {
}