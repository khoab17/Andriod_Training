package com.syedabdullah.newsstream.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.syedabdullah.newsstream.databinding.FragmentNewsFeedBinding
import com.syedabdullah.newsstream.network.InternetConnection
import com.syedabdullah.newsstream.ui.adapter.NewsAdapter
import com.syedabdullah.newsstream.viewmodel.NewsViewModel

class NewsFeedFragment(): Fragment() {
    private var _binding : FragmentNewsFeedBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel:NewsViewModel
    private val navArgs : NewsFeedFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[NewsViewModel::class.java]
        recyclerView = binding.recyclerNewsView
        recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.articles.observe(viewLifecycleOwner,Observer {
                binding.recyclerNewsView.adapter = NewsAdapter(viewModel.articles.value!!, viewModel)
        })

        val swipeToRefresh = binding.swipeToRefresh
        swipeToRefresh.setOnRefreshListener {
            if(InternetConnection.isOnline(requireContext())){
                viewModel.fetchApiNewsByCategory(navArgs.selectedCategory)
            }
            else{
                Snackbar.make(view,"Please Check Your Internet Connection !", Snackbar.LENGTH_LONG).show()
            }
            swipeToRefresh.isRefreshing =false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}