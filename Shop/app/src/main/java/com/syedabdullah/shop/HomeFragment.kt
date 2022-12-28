package com.syedabdullah.shop

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syedabdullah.shop.adapter.ProductRecyclerViewAdapter
import com.syedabdullah.shop.databinding.FragmentHomeBinding
import com.syedabdullah.shop.model.Product
import com.syedabdullah.shop.viewmodel.ProductViewModel
import com.syedabdullah.shop.viewmodel.ProductViewModelProviderFactory

class HomeFragment : Fragment() {
    private var _binding:FragmentHomeBinding?=null
    private val binding get()=_binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var productViewModel: ProductViewModel
    private val layoutManager=LinearLayoutManager(context)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=binding.recyclerViewProductList
        val factory=ProductViewModelProviderFactory()
        productViewModel= ViewModelProvider(this,factory)[ProductViewModel::class.java]

       binding.buttonAddProduct.setOnClickListener {
           addProduct()
       }
        initializeAdapter()
        observeData()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
    
    private fun addProduct(){
        binding.apply {
            if(editProductName.text.isBlank() || editProductDetails.text.isBlank() || editProductPrice.text.isBlank()){
                Toast.makeText(context, "Provide all values", Toast.LENGTH_SHORT).show()
            }
            else{
                val product=Product(editProductName.text.toString(),editProductDetails.text.toString(),
                    editProductPrice.text.toString().toDouble())
                productViewModel.addProduct(product)
                editProductName.text.clear()
                editProductDetails.text.clear()
                editProductPrice.text.clear()
            }
        }
    }


    private fun initializeAdapter(){
        recyclerView.layoutManager=layoutManager
        observeData()
    }
    private fun observeData(){
        Log.d("check", "observeData: called")
        productViewModel.list.observe(viewLifecycleOwner) {
            recyclerView.adapter = ProductRecyclerViewAdapter(requireContext(), productViewModel, it)
        }
    }
}