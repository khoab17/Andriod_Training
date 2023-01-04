package com.syedabdullah.roomdbtask.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.syedabdullah.roomdbtask.R
import com.syedabdullah.roomdbtask.databinding.FragmentAddShopBinding
import com.syedabdullah.roomdbtask.databinding.FragmentHomeBinding
import com.syedabdullah.roomdbtask.model.Shop
import com.syedabdullah.roomdbtask.viewmodel.ShopViewModel
import java.time.LocalDate
import java.util.Date

class AddShopFragment : Fragment() {

    private var _binding:FragmentAddShopBinding?=null
    private val binding get() = _binding!!
    private lateinit var viewModel: ShopViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ShopViewModel::class.java]
        binding.apply {
            addButton.setOnClickListener {
                if(editShopName.text.toString().isBlank() || editShopType.text.toString().isBlank() || editShopDescription.text.toString().isBlank())
                {
                    Toast.makeText(requireContext(), "Provide all values", Toast.LENGTH_SHORT).show()
                }
                else {
                    val shop = Shop(
                        0,
                        editShopName.text.toString(),
                        editShopType.text.toString(),
                        editShopDescription.text.toString(),
                        Date()
                    )
                    viewModel.addShop(shop)
                    findNavController().navigate(R.id.homeFragment)
                }
            }
        }
    }
}