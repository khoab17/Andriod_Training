package com.syedabdullah.fragmentnavigation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.syedabdullah.fragmentnavigation.R
import com.syedabdullah.fragmentnavigation.model.User
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_send_data_profile_to_about.setOnClickListener {
            val userId=edit_text_user_id.text.toString().toIntOrNull()
            val userName=edit_text_user_name.text.toString()
            val userEmail=edit_text_user_email.text.toString()

            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToAboutFragment(user =
            User(userId?:0,userName,userEmail)
            ))
        }

        btn_profile_to_home.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToHomeFragment())
        }
    }
}