package com.syedabdullah.newsstream.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syedabdullah.newsstream.R
import com.syedabdullah.newsstream.databinding.FragmentBookmarkBinding
import com.syedabdullah.newsstream.ui.adapter.BookmarkAdapter
import com.syedabdullah.newsstream.ui.adapter.NewsAdapter
import com.syedabdullah.newsstream.viewmodel.NewsViewModel

class BookmarkFragment : Fragment() {
    private var _binding:FragmentBookmarkBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView:RecyclerView
    private val viewModel: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerBookmarkView
        recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.bookmarks.observe(viewLifecycleOwner, Observer { articles ->
            binding.recyclerBookmarkView.adapter = BookmarkAdapter(viewModel.bookmarks.value!!, viewModel)
        })

    }
}