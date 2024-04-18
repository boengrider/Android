package com.example.imageslider

import android.app.Activity
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(activity:AppCompatActivity,
                   private val slides:List<Int>):
    FragmentStateAdapter(activity) {

    override fun getItemCount() = slides.size

    override fun createFragment(position: Int):Fragment {
        return PagerFragment.newInstance(position)
    }

}