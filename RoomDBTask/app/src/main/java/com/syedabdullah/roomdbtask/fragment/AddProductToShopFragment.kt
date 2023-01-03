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
import com.syedabdullah.roomdbtask.R
import com.syedabdullah.roomdbtask.databinding.FragmentAddProductToShopBinding
import com.syedabdullah.roomdbtask.databinding.FragmentProductListBinding
import com.syedabdullah.roomdbtask.model.Product
import com.syedabdullah.roomdbtask.viewmodel.ShopViewModel

class AddProductToShopFragment : Fragment() {
    private val navArgs by navArgs<AddProductToShopFragmentArgs>()
    private var _binding: FragmentAddProductToShopBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ShopViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddProductToShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ShopViewModel::class.java]

        binding.apply {
            buttonAddProduct.setOnClickListener {
                if (editProductName.text.toString()
                        .isBlank() || editProductDescription.text.toString()
                        .isBlank() || editProductPrice.text.toString().isBlank()
                ) {
                    Toast.makeText(requireContext(), "Provide all details", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val product = Product(
                        0,
                        editProductName.text.toString(),
                        editProductDescription.text.toString(),
                        editProductPrice.text.toString().toDouble(),
                        navArgs.currentShop.id
                    )
                    viewModel.addProduct(product)
                    val action = AddProductToShopFragmentDirections.actionAddProductToShopFragmentToProductListFragment(navArgs.currentShop)
                    findNavController().navigate(action)
                }
            }
        }
    }

}