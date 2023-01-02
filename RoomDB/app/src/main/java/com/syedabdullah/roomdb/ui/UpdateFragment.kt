package com.syedabdullah.roomdb.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.syedabdullah.roomdb.R
import com.syedabdullah.roomdb.databinding.FragmentUpdateBinding
import com.syedabdullah.roomdb.model.User
import com.syedabdullah.roomdb.viewmodel.UserViewModel

class UpdateFragment : Fragment() {

    private val navArgs by navArgs<UpdateFragmentArgs>()
    private var _binding:FragmentUpdateBinding?=null
    private val binding get() = _binding!!
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentUpdateBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel=ViewModelProvider(this)[UserViewModel::class.java]

        binding.apply {
            firstnameEt.setText(navArgs.currentUser.firstName)
            lastnameEt.setText(navArgs.currentUser.lastName)
            ageEt.setText(navArgs.currentUser.age.toString())

            buttonUpdateUser.setOnClickListener {
                if(firstnameEt.text.toString().isBlank() || lastnameEt.text.toString().isBlank() || ageEt.text.toString().isBlank())
                {
                    Toast.makeText(requireContext(), "Provide all the values", Toast.LENGTH_SHORT).show()
                }
                else {
                    val user = User(
                        navArgs.currentUser.userId,
                        firstnameEt.text.toString(),
                        lastnameEt.text.toString(),
                        ageEt.text.toString().toInt()
                    )
                    Log.d("check", "onViewCreated: $user")
                    viewModel.updateUser(user)
                    findNavController().navigate(R.id.action_updateFragment_to_usersFragment)
                }
            }
        }
    }
}