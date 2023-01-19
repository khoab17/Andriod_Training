package com.syedabdullah.newsstream.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.syedabdullah.newsstream.databinding.FragmentHomeBinding
import com.syedabdullah.newsstream.ui.adapter.ViewPagerAdapter
import com.syedabdullah.newsstream.viewmodel.NewsViewModel


class HomeFragment : Fragment() {
    private var _binding:FragmentHomeBinding? =null
    private val binding get() = _binding!!
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =FragmentHomeBinding.inflate(inflater,container,false)
        // viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabLayout = binding.tabLayout
        val viewPage = binding.viewPager

        val tabAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        viewPage.adapter = tabAdapter
        TabLayoutMediator(tabLayout, viewPage) { tab, position ->
            when (position){
                0->tab.text = "Top News"
                1->tab.text = "US News"
                2->tab.text="Business"
                3->tab.text="Entertainment"
                else->tab.text="Sports"
            }
        }.attach()

        tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                when(tab.position){
                    0-> Log.d("tag", "onTabSelected: tab 1 ")
                    1-> Log.d("tag", "onTabSelected: tab 2 ")
                    2-> Log.d("tag", "onTabSelected: tab 3 ")
                    3-> Log.d("tag", "onTabSelected: tab 4 ")
                    else-> Log.d("tag", "onTabSelected: tab  5")
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        }

        )
    }
}