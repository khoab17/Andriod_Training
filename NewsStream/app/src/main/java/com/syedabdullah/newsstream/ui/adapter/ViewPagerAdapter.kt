package com.syedabdullah.newsstream.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.syedabdullah.newsstream.ui.HomeFragment
import com.syedabdullah.newsstream.ui.NewsFeedFragment

private const val NUMS_TAB = 5

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fragmentManager, lifecycle)
{
    override fun getItemCount(): Int {
        return NUMS_TAB
    }


    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->NewsFeedFragment()
            1->NewsFeedFragment()
            2->NewsFeedFragment()
            else->Fragment()
        }
    }

}