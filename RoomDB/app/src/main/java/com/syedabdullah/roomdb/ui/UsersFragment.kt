package com.syedabdullah.roomdb.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syedabdullah.roomdb.R
import com.syedabdullah.roomdb.databinding.FragmentUsersBinding
import com.syedabdullah.roomdb.viewmodel.UserViewModel

class UsersFragment : Fragment() {

    private var _binding:FragmentUsersBinding ? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: UserViewModel
    private val layoutManager=LinearLayoutManager(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this,)
        binding.favAddUser.setOnClickListener {
            findNavController().navigate(R.id.action_usersFragment_to_addUserFragment)
        }
    }
}