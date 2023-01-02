package com.syedabdullah.roomdb.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syedabdullah.roomdb.R
import com.syedabdullah.roomdb.databinding.FragmentUsersBinding
import com.syedabdullah.roomdb.ui.adapter.UserAdapter
import com.syedabdullah.roomdb.viewmodel.UserViewModel

class UsersFragment : Fragment() {

    private var _binding:FragmentUsersBinding ? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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
        userViewModel=ViewModelProvider(this)[UserViewModel::class.java]
        recyclerView=binding.recyclerViewUsers
        val adapter=UserAdapter(requireContext(),userViewModel)
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=adapter
        userViewModel.readAllUser.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        binding.favAddUser.setOnClickListener {
            findNavController().navigate(R.id.action_usersFragment_to_addUserFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.top_menu_bar,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.clear_all_users -> {
                userViewModel.deleteAllUser()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}