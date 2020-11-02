package com.example.basicproject.cards

import com.example.basicproject.R

object Cards {
    var cards = arrayListOf(
        Card("polypollya", R.drawable.im_1,"Мое маленькое чудо", ViewPagerAdapter(
                arrayListOf(
                    R.drawable.card_1,
                    R.drawable.card_2,
                    R.drawable.card_3
                ))
        ),
        Card("bulat_din", R.drawable.im_2,"Зайки", ViewPagerAdapter(
                arrayListOf(
                    R.drawable.card_4,
                    R.drawable.card_5,
                    R.drawable.card_6
                ))
        ),
        Card("poomogite", R.drawable.im_2,"*приветульки*", ViewPagerAdapter(
            arrayListOf(
                R.drawable.card_3,
                R.drawable.card_6,
                R.drawable.card_2
            ))
        )

        )

}