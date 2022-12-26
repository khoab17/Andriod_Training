package com.syedabdullah.todo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.syedabdullah.fragmentnavigation.R
import com.syedabdullah.fragmentnavigation.databinding.FragmentAddTaskBinding
import com.syedabdullah.todo.data.ToDoRepo
import com.syedabdullah.todo.model.ToDo
import java.time.LocalDate
import java.time.LocalTime
import java.util.Calendar

class AddTaskFragment : Fragment() {
    private  var _binding:FragmentAddTaskBinding?=null
    private val binding get()=_binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentAddTaskBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAddTask.setOnClickListener {
            val title=binding.editTextTitle.text.toString()
            val description=binding.editTextDescription.text.toString()
            ToDoRepo().addTodo(ToDo(title,description,Calendar.getInstance().time.toString(),Calendar.getInstance().time.toString(),false))

            findNavController().navigate(R.id.todoListFragment)
        }
    }
}