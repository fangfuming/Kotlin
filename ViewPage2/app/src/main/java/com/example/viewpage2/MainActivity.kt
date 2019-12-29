package com.example.viewpage2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager2.adapter = object : FragmentStateAdapter(this){
            override fun getItemCount() = 3

            override fun createFragment(position: Int)  = when(position){
                0 -> FragmentOne()
                1 -> FragmentTwo()
                else ->FragmentThree()
            }
        }
        TabLayoutMediator(tab_layout,viewPager2){
            tab, position ->
            when(position){
                0 ->tab.text = "旋转"
                1 ->tab.text = "缩放"
                else ->tab.text = "平移"
            }
        }.attach()
    }
}
