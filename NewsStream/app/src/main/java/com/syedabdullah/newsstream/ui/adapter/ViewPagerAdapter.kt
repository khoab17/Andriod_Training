package com.syedabdullah.newsstream.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.syedabdullah.newsstream.ui.NewsFeedFragment
import com.syedabdullah.newsstream.util.ClassConverter
import com.syedabdullah.newsstream.viewmodel.NewsViewModel



class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, private val viewModel: NewsViewModel):
    FragmentStateAdapter(fragmentManager, lifecycle)
{
    private val tabs = 5

    override fun getItemCount(): Int {
        return tabs
    }

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle()
        when (position){
            0->{
                bundle.putString("selected_category", ClassConverter.TOP_NEWS)
            }1->{
            bundle.putString("selected_category", ClassConverter.GENERAL)
            }2->{
                bundle.putString("selected_category", ClassConverter.BUSINESS)
            }3->{
                bundle.putString("selected_category", ClassConverter.ENTERTAINMENT)
            }
            else->{
                bundle.putString("selected_category", ClassConverter.SPORTS)
            }
        }
        val fragment = NewsFeedFragment()
        fragment.arguments = bundle
        return fragment
    }

}