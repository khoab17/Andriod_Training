package com.syedabdullah.todo.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.syedabdullah.fragmentnavigation.R
import com.syedabdullah.todo.data.ToDoRepo
import com.syedabdullah.todo.model.ToDo

class TodoListAdapter:RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>() {

    private var todoList:List<ToDo> = ToDoRepo().getToDo()
    init {
        Log.d("check", "adapter outer class: ")
    }

    class TodoListViewHolder(private val view: View):RecyclerView.ViewHolder(view){
        init {
            Log.d("check", "init block called() inner class")
        }
        val title: TextView =view.findViewById(R.id.tv_title)
        val description:TextView=view.findViewById(R.id.tv_description)
        val time:TextView=view.findViewById(R.id.tv_time)
        val status:CheckBox=view.findViewById(R.id.check_box_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        val layout=LayoutInflater.from(parent.context).inflate(
            R.layout.todo_list_view,parent,false
        )
        return TodoListViewHolder(layout)
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        holder.title.text=todoList[position].title
        holder.description.text=todoList[position].description
        holder.time.text=todoList[position].time
        holder.status.isChecked=todoList[position].status

    }

    override fun getItemCount(): Int {
        Log.d("check", "getItemCount: ${todoList.size} ")
        return todoList.size
    }
}