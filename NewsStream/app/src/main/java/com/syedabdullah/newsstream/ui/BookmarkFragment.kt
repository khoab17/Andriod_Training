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
import com.syedabdullah.newsstream.databinding.FragmentBookmarkBinding
import com.syedabdullah.newsstream.ui.adapter.BookmarkAdapter
import com.syedabdullah.newsstream.viewmodel.NewsViewModel

class BookmarkFragment : Fragment() {
    private var _binding:FragmentBookmarkBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView:RecyclerView
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[NewsViewModel::class.java]
        viewModel.getBookmarks()
        recyclerView = binding.recyclerBookmarkView
        recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.bookmarks.observe(viewLifecycleOwner, Observer {
            binding.recyclerBookmarkView.adapter = BookmarkAdapter(viewModel.bookmarks.value!!, viewModel)
        })

    }
}