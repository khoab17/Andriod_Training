package com.syedabdullah.newsstream.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.syedabdullah.newsstream.databinding.FragmentHomeBinding
import com.syedabdullah.newsstream.network.InternetConnection
import com.syedabdullah.newsstream.ui.adapter.ViewPagerAdapter
import com.syedabdullah.newsstream.viewmodel.Constant
import com.syedabdullah.newsstream.viewmodel.Constant.Companion.BUSINESS
import com.syedabdullah.newsstream.viewmodel.Constant.Companion.ENTERTAINMENT
import com.syedabdullah.newsstream.viewmodel.Constant.Companion.GENERAL
import com.syedabdullah.newsstream.viewmodel.Constant.Companion.SPORTS
import com.syedabdullah.newsstream.viewmodel.Constant.Companion.TOP_NEWS
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[NewsViewModel::class.java]
        viewModel.getNewsByCategory(TOP_NEWS)
        val tabLayout = binding.tabLayout
        val viewPage = binding.viewPager
        val tabAdapter = ViewPagerAdapter(childFragmentManager, lifecycle, viewModel)
        viewPage.adapter = tabAdapter
        TabLayoutMediator(tabLayout, viewPage) { tab, position ->
            when (position){
                0->tab.text = "Top News"
                1->tab.text = "General"
                2->tab.text="Business"
                3->tab.text="Entertainment"
                else->tab.text="Sports"
            }
        }.attach()

        tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                when(tab.position){
                    0-> {
                        viewModel.getNewsByCategory(TOP_NEWS)
                    }
                    1-> {
                        viewModel.getNewsByCategory(GENERAL)
                    }
                    2-> {
                        viewModel.getNewsByCategory(BUSINESS)
                    }
                    3-> {
                        viewModel.getNewsByCategory(ENTERTAINMENT)
                    }
                    else->{
                        viewModel.getNewsByCategory(SPORTS)
                    }
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