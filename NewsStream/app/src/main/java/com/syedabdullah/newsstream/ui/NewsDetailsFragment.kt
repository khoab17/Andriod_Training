package com.syedabdullah.newsstream.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.syedabdullah.newsstream.R
import com.syedabdullah.newsstream.databinding.FragmentNewsDetailsBinding
import com.syedabdullah.newsstream.util.ClassConverter

class NewsDetailsFragment : Fragment() {
    private var _binding: FragmentNewsDetailsBinding? = null
    private val binding get() = _binding!!
    private val navArgs: NewsDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (navArgs.article.urlToImage != null) {
            Glide.with(requireContext())
                .load(navArgs.article.urlToImage)
                .placeholder(R.drawable.loading_animation).centerCrop()
                .error(R.drawable.no_image_available)
                .into(binding.imageNewsDetails)
        } else {
            Glide.with(requireContext())
                .load(R.drawable.no_image_available)
                .into(binding.imageNewsDetails)
        }
        binding.tvTitleNewsDetails.text = navArgs.article.title
        binding.tvDescriptionNewsDetails.text = navArgs.article.description
        binding.dateNewsDetails.text = ClassConverter.dateFormat(navArgs.article.publishedAt.toString())

        binding.buttonContinueReading.setOnClickListener {
            val action = NewsDetailsFragmentDirections.actionNewsDetailsFragmentToWebViewFragment(navArgs.article.url!!)
            findNavController().navigate(action)
        }
    }
}