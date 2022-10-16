package com.example.panelsurya

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class PagerAdapter(fm: FragmentManager?, var mNoOfTabs: Int) :
    FragmentStatePagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                HomeFragment()
            }
            1 -> {
                HistoryFragment()
            }
            else -> null!!
        }
    }

    override fun getCount(): Int {
        return mNoOfTabs
    }
}