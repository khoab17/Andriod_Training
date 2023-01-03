package com.syedabdullah.roomdbtask.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syedabdullah.roomdbtask.R
import com.syedabdullah.roomdbtask.databinding.FragmentHomeBinding
import com.syedabdullah.roomdbtask.fragment.adapter.ShopAdapter
import com.syedabdullah.roomdbtask.viewmodel.ShopViewModel

class HomeFragment : Fragment() {
    private var _binding:FragmentHomeBinding?=null
    private val binding get() = _binding!!
    private lateinit var viewModel:ShopViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ShopViewModel::class.java]

        binding.apply {
            recyclerView = recyclerViewShops
            recyclerView.layoutManager =LinearLayoutManager(requireContext())
            val adapter =ShopAdapter(requireContext(), viewModel)
            recyclerView.adapter = adapter

            viewModel.readAllData.observe(viewLifecycleOwner){
                adapter.setData(it)
            }

            favAddShop.setOnClickListener {
                findNavController().navigate(R.id.addShopFragment)
            }
        }
    }
}