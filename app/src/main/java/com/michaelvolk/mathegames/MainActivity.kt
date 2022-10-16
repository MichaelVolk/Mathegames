package com.michaelvolk.mathegames

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewpager: ViewPager2 = findViewById(R.id.pager)
        val fragments: ArrayList<Fragment> = arrayListOf(
            Klasse5Fragment(),
            Klasse6Fragment(),
            Klasse7Fragment(),
            Klasse8Fragment(),
            Klasse9Fragment()
        )
        val tabLayout: TabLayout = findViewById(R.id.tab_layout)
        val adapter = ViewPagerAdapter(fragments, this)
        viewpager.adapter = adapter
        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            tab.text = "Klasse ${(position + 5)}"
        }.attach()


    }
}