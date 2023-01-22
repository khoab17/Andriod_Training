package com.syedabdullah.newsstream.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.syedabdullah.newsstream.R
import com.syedabdullah.newsstream.databinding.FragmentNewsFeedBinding
import com.syedabdullah.newsstream.databinding.FragmentWebViewBinding

class WebViewFragment : Fragment() {
    private var _binding:FragmentWebViewBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWebViewBinding.inflate(inflater,container,false)
        return binding.root
    }
}