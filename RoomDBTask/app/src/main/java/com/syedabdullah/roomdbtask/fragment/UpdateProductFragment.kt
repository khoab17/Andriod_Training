package com.syedabdullah.roomdbtask.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.syedabdullah.roomdbtask.databinding.FragmentUpdateProductBinding
import com.syedabdullah.roomdbtask.model.Product
import com.syedabdullah.roomdbtask.viewmodel.ShopViewModel

class UpdateProductFragment : Fragment() {
    private val navArgs by navArgs<UpdateProductFragmentArgs>()
    private var _binding:FragmentUpdateProductBinding?=null
    private val binding get() = _binding!!
    private lateinit var viewModel: ShopViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentUpdateProductBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ShopViewModel::class.java]

        binding.apply {
            editProductName.setText(navArgs.currentProduct.name)
            editProductPrice.setText(navArgs.currentProduct.price.toString())
            editProductDescription.setText(navArgs.currentProduct.description)

            buttonUpdateProduct.setOnClickListener {
                if(editProductName.text.toString().isBlank() || editProductPrice.text.toString().isBlank() || editProductDescription.text.toString().isBlank())
                {
                    Toast.makeText(requireContext(), "Provide all the values", Toast.LENGTH_SHORT).show()
                }
                else{

                    val product = Product(
                        navArgs.currentProduct.id,
                        editProductName.text.toString(),
                        editProductDescription.text.toString(),
                        editProductPrice.text.toString().toDouble(),
                        navArgs.currentProduct.shopId
                    )
                    viewModel.updateProduct(product)
                    //val shop:Shop = viewModel.getShop(navArgs.currentProduct.shopId)
                    val action = UpdateProductFragmentDirections.actionUpdateProductFragmentToProductListFragment(navArgs.currentShop)
                    findNavController().navigate(action)
                }
            }
        }
    }
}