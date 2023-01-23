package com.syedabdullah.newsstream.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.syedabdullah.newsstream.R
import com.syedabdullah.newsstream.databinding.FragmentHomeBinding
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
    private var currentTab =Constant.TOP_NEWS

    private lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
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
                        currentTab= TOP_NEWS
                    }
                    1-> {
                        viewModel.getNewsByCategory(GENERAL)
                        currentTab = GENERAL
                    }
                    2-> {
                        viewModel.getNewsByCategory(BUSINESS)
                        currentTab = BUSINESS
                    }
                    3-> {
                        viewModel.getNewsByCategory(ENTERTAINMENT)
                        currentTab = ENTERTAINMENT
                    }
                    else-> {
                        viewModel.getNewsByCategory(SPORTS)
                        currentTab = SPORTS
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_menu_bar, menu)
        val item = menu?.findItem(R.id.action_search)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchNews(query.toString())
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if(newText.isEmpty()){
                    viewModel.getNewsByCategory(currentTab)
                }
                return true
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

}