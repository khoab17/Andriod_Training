package com.syedabdullah.fragmentnavigation.fragment

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.syedabdullah.fragmentnavigation.R
import com.syedabdullah.fragmentnavigation.model.User
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : Fragment() {
    val args:AboutFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_user_id_about.text="User id:${args.user?.userId.toString()}"
        tv_user_name_about.text="Name:${args.user?.userName.toString()}"
        tv_user_email_about.text="Email:${args.user?.email.toString()}"

        btn_about_to_home.setOnClickListener {
            findNavController().navigate(AboutFragmentDirections.actionAboutFragmentToHomeFragment())
        }
        btn_about_to_profile.setOnClickListener {
            findNavController().navigate(AboutFragmentDirections.actionAboutFragmentToProfileFragment())
        }
    }
}