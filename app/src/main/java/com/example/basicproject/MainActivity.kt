package com.example.basicproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import com.example.basicproject.cards.CardFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var cardFragment: CardFragment
    lateinit var textFragment: TextFragment
    lateinit var listFragment: ListFragment
    lateinit var lastFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentId = R.id.frame_layout
        textFragment = TextFragment()
        listFragment = ListFragment()
        cardFragment = CardFragment()
        supportFragmentManager.beginTransaction()
            .add(fragmentId, textFragment)
            .add(fragmentId, listFragment)
            .add(fragmentId, cardFragment)
            .hide(listFragment)
            .hide(cardFragment)
            .commit()
        lastFragment = cardFragment

        bottom_nav_bar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.page_1 -> {
                    changeFragment(textFragment)
                    lastFragment = textFragment
                    true
                }
                R.id.page_2 -> {
                    changeFragment(listFragment)
                    lastFragment = listFragment
                    true
                }
                R.id.page_3 -> {
                    changeFragment(cardFragment)
                    lastFragment = cardFragment
                    true
                }
                else -> false
            }
        }
    }
    private fun changeFragment(Fragment: Fragment) {
        if (Fragment != lastFragment)
            if (
                Fragment == textFragment && (lastFragment == listFragment || lastFragment == cardFragment) ||
                Fragment == listFragment && lastFragment == cardFragment
            )
                supportFragmentManager.beginTransaction()
                    .hide(lastFragment)
                    .show(Fragment)
                    .commit()
            else
                supportFragmentManager.beginTransaction()
                    .hide(lastFragment)
                    .show(Fragment)
                    .commit()
    }
}