package com.syedabdullah.newsstream.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.syedabdullah.newsstream.R
import com.syedabdullah.newsstream.databinding.FragmentNewsDetailsBinding

class NewsDetailsFragment : Fragment() {
    private var _binding:FragmentNewsDetailsBinding? = null
    private val binding get() = _binding!!
    private val navArgs:NewsDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.webViewNewsDetails.loadUrl(navArgs)
        Log.d("info", "onViewCreated: ${navArgs.article}")
        if(navArgs.article.urlToImage !=null ){
            Glide.with(requireContext())
                .load(navArgs.article.urlToImage)
                .placeholder(R.drawable.loading_animation).centerCrop()
                .error(R.drawable.no_image_available)
                .into(binding.imageNewsDetails)
        }
        else{
            Glide.with(requireContext())
                .load(R.drawable.no_image_available)
                .into(binding.imageNewsDetails)
        }
        Log.d("info", "onViewCreated: ${navArgs.article.title+"=========="+navArgs.article.description}")
        binding.tvTitleNewsDetails.text = navArgs.article.title.toString()
        binding.tvDescriptionNewsDetails.text = navArgs.article.description.toString()
        binding.dateNewsDetails.text =navArgs.article.publishedAt

    }
}