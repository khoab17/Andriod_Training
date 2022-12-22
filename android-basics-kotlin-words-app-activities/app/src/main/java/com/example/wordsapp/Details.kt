package com.example.wordsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wordsapp.databinding.FragmentDetailsBinding
import com.example.wordsapp.databinding.FragmentWordListBinding


class Details : Fragment() {
    private var _binding:FragmentDetailsBinding?= null
    private val binding get()=_binding!!
    private lateinit var searchContext:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            searchContext=Constants.SEARCH_PREFIX+it.getString("word").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.search.text=searchContext
    }

}