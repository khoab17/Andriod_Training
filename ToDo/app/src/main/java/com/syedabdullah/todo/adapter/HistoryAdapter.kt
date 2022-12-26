package com.syedabdullah.todo.adapter

import android.annotation.SuppressLint
import android.content.ClipData.Item
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

class HistoryAdapter:RecyclerView.Adapter<HistoryAdapter.ItemViewHolder>() {
    private var historyTodoList:List<ToDo> = ToDoRepo().getHistory()

    class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val title: TextView =view.findViewById(R.id.tv_title)
        val description: TextView =view.findViewById(R.id.tv_description)
        val time: TextView =view.findViewById(R.id.tv_time)
        val status: TextView =view.findViewById(R.id.tv_status)
        init {
            Log.d("check", "okay: ")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layout= LayoutInflater.from(parent.context).inflate(
            R.layout.todo_list_history_view,parent,false
        )
        return ItemViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.title.text=historyTodoList[position].title
        holder.description.text=historyTodoList[position].description
        "Time:${historyTodoList[position].time}".also { holder.time.text = it }
        if(historyTodoList[position].status){
            "Status:Completed".also { holder.status.text = it }
        }
        else "Status: Incomplete".also { holder.status.text = it }

    }

    override fun getItemCount(): Int {
        return historyTodoList.size
    }
}