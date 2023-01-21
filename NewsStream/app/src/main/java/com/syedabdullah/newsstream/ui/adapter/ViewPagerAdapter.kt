package com.syedabdullah.newsstream.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.syedabdullah.newsstream.ui.NewsFeedFragment
import com.syedabdullah.newsstream.viewmodel.Constant
import com.syedabdullah.newsstream.viewmodel.NewsViewModel

private const val NUMS_TAB = 5

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, private val viewModel: NewsViewModel):
    FragmentStateAdapter(fragmentManager, lifecycle)
{
    override fun getItemCount(): Int {
        return NUMS_TAB
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0->NewsFeedFragment(viewModel,Constant.TOP_NEWS)
            1->NewsFeedFragment(viewModel,Constant.GENERAL)
            2->NewsFeedFragment(viewModel,Constant.BUSINESS)
            3->NewsFeedFragment(viewModel,Constant.ENTERTAINMENT)
            else->NewsFeedFragment(viewModel,Constant.SPORTS)
        }
    }

}