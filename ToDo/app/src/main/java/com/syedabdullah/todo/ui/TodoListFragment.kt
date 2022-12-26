package com.syedabdullah.todo.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.syedabdullah.fragmentnavigation.R
import com.syedabdullah.fragmentnavigation.databinding.FragmentTodoListBinding
import com.syedabdullah.todo.adapter.TodoListAdapter
import com.syedabdullah.todo.data.ToDoRepo

class TodoListFragment : Fragment() {
    private var _binding:FragmentTodoListBinding ? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("check", "onCreate: ()${ToDoRepo().getToDo()}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTodoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=binding.recyclerViewTodoList
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=TodoListAdapter()
        Log.d("check", "onViewCreated: () ToDoListFragment")

        binding.favAddTask.setOnClickListener {
            findNavController().navigate(R.id.addTaskFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}