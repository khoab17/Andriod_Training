package com.syedabdullah.roomdb.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.syedabdullah.roomdb.R
import com.syedabdullah.roomdb.databinding.FragmentAddUserBinding
import com.syedabdullah.roomdb.model.User
import com.syedabdullah.roomdb.viewmodel.UserViewModel

class AddUserFragment : Fragment() {
    private  var _binding:FragmentAddUserBinding?=null
    private val binding get()=_binding!!
    private lateinit var viewModel:UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentAddUserBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this)[UserViewModel::class.java]

        binding.addButton.setOnClickListener {
            addUserToDatabase()
        }
    }

    private fun addUserToDatabase() {
        val firstName=binding.firstnameEt.text.toString()
        val lastName=binding.lastnameEt.text.toString()
        val age=binding.ageEt.text.toString()

        if(firstName.isBlank() || lastName.isBlank() || age.isBlank())
        {
            Toast.makeText(requireContext(), "Provide all the values", Toast.LENGTH_SHORT).show()
        }
        else{
            val user= User(0,firstName,lastName,age.toInt())
            viewModel.addUser(user)
            findNavController().navigate(R.id.action_addUserFragment_to_usersFragment)
        }
    }
}