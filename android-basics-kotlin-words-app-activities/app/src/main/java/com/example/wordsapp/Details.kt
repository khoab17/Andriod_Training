package com.example.wordsapp

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.StructuredName.PREFIX
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.wordsapp.Constants.Companion.WORD
import com.example.wordsapp.databinding.FragmentDetailsBinding


class Details : Fragment() {
    private var _binding:FragmentDetailsBinding?= null
    private val binding get()=_binding!!
    private lateinit var searchContext:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            searchContext=PREFIX+it.getString(WORD).toString()
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
        val webView=binding.webView
        webView.webViewClient= WebViewClient()
        webView.loadUrl(searchContext)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}