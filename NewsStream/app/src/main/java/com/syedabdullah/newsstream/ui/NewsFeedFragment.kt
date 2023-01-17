package com.syedabdullah.newsstream.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syedabdullah.newsstream.databinding.FragmentNewsFeedBinding
import com.syedabdullah.newsstream.model.Article
import com.syedabdullah.newsstream.ui.adapter.NewsAdapter
import com.syedabdullah.newsstream.viewmodel.NewsViewModel

class NewsFeedFragment : Fragment() {
    private var _binding : FragmentNewsFeedBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val tabLayout = binding.tabLayoutHome
//        val viewPage = binding.viewPager2
//
//        val tabAdapter = TabAdapter(childFragmentManager, lifecycle)
//        viewPage.adapter = tabAdapter
//        TabLayoutMediator(tabLayout, viewPage) { tab, position ->
//            tab.text = tabList[position].title
//        }.attach()

        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        recyclerView = binding.recyclerNewsView
        recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.articles.observe(viewLifecycleOwner,Observer { articles ->
                binding.recyclerNewsView.adapter = NewsAdapter(viewModel.articles.value!!)
        })
    }
}