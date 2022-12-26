
package com.syedabdullah.todo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syedabdullah.fragmentnavigation.databinding.FragmentHistoryBinding
import com.syedabdullah.todo.adapter.HistoryAdapter


class HistoryFragment : Fragment() {
    private var _binding:FragmentHistoryBinding?=null
    private val binding get() = _binding!!
    private lateinit var recyclerView:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentHistoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=binding.recyclerViewTodoListHistory
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=HistoryAdapter()
    }

}