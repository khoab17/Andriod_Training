package com.syedabdullah.roomdbtask.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syedabdullah.roomdbtask.R
import com.syedabdullah.roomdbtask.databinding.FragmentProductListBinding
import com.syedabdullah.roomdbtask.fragment.adapter.ProductAdapter
import com.syedabdullah.roomdbtask.model.Product
import com.syedabdullah.roomdbtask.viewmodel.ShopViewModel
import kotlinx.coroutines.coroutineScope

class ProductListFragment : Fragment() {
    private val navArgs by navArgs<ProductListFragmentArgs>()
    private var _binding:FragmentProductListBinding?=null
    private val binding get()=_binding!!
    private lateinit var viewModel: ShopViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ShopViewModel::class.java]

        binding.apply {
            viewModel.getProducts(navArgs.currentShop.id)
            recyclerView=recyclerViewProducts
            recyclerView.layoutManager=LinearLayoutManager(requireContext())
            val adapter = ProductAdapter(requireContext()
                ,viewModel)
            recyclerView.adapter=adapter
            viewModel.readAllProduct.observe(viewLifecycleOwner){
                adapter.setData(it)
            }

            favAddProduct.setOnClickListener {
                val action = ProductListFragmentDirections.actionProductListFragmentToAddProductToShopFragment(navArgs.currentShop)
                findNavController().navigate(action)
            }
        }
    }

}