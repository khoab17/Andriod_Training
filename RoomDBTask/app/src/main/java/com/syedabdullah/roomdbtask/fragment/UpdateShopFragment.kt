package com.syedabdullah.roomdbtask.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.syedabdullah.roomdbtask.R
import com.syedabdullah.roomdbtask.databinding.FragmentUpdateShopBinding
import com.syedabdullah.roomdbtask.model.Shop
import com.syedabdullah.roomdbtask.viewmodel.ShopViewModel

class UpdateShopFragment : Fragment() {
    private val navArgs by navArgs<UpdateShopFragmentArgs>()
    private var _binding: FragmentUpdateShopBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ShopViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ShopViewModel::class.java]

        binding.apply {
            editShopName.setText(navArgs.currentShop.name)
            editShopType.setText(navArgs.currentShop.type)
            editShopDescription.setText(navArgs.currentShop.description)

            buttonUpdateShop.setOnClickListener {
                if (editShopName.text!!.isBlank() || editShopType.text!!.isBlank() || editShopDescription.text!!.isBlank()) {
                    Toast.makeText(requireContext(), "Provide all the details", Toast.LENGTH_SHORT).show()
                } else {
                    val shop = Shop(
                        navArgs.currentShop.id,
                        editShopName.text.toString(),
                        editShopType.text.toString(),
                        editShopDescription.text.toString(),
                        navArgs.currentShop.date
                    )
                    viewModel.updateShop(shop)
                    findNavController().navigate(R.id.homeFragment)
                }
            }

            buttonUpdatePicture.setOnClickListener {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                startActivityForResult(intent,22)

            }
        }
    }
}