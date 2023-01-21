package com.syedabdullah.newsstream.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syedabdullah.newsstream.databinding.FragmentNewsFeedBinding
import com.syedabdullah.newsstream.model.Article
import com.syedabdullah.newsstream.ui.adapter.NewsAdapter
import com.syedabdullah.newsstream.viewmodel.NewsViewModel

class NewsFeedFragment(private val viewModel: NewsViewModel,private val selectedTabCategory:String): Fragment() {
    private var _binding : FragmentNewsFeedBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerNewsView
        recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.articles.observe(viewLifecycleOwner,Observer { articles ->
                binding.recyclerNewsView.adapter = NewsAdapter(viewModel.articles.value!!, viewModel)
        })

        val swipeToRefresh = binding.swipeToRefresh
        swipeToRefresh.setOnRefreshListener {
            Log.d("news", "onViewCreated:  ")
            viewModel.fetchApiNewsByCategory(selectedTabCategory)
            swipeToRefresh.isRefreshing =false
        }
    }
}