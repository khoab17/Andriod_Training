package com.syedabdullah.affirmation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.syedabdullah.affirmation.R
import com.syedabdullah.affirmation.model.Student

class ItemAdapter(private val context:Context ,private val dataset:List<Student>):
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>()

{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        //Log.d("Demo",(10).toString())
        val adapterLayout=LayoutInflater.from(parent.context).inflate(R.layout.list_student,parent,false)
        return ItemViewHolder(adapterLayout)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.textViewID.text="Student ID: ${dataset[position].studentId.toString()}"
        holder.textViewName.text="Name: ${dataset[position].name}"
        holder.textViewBloodGroup.text="Blood Group: ${dataset[position].bloodGroup}"
        holder.imageView.setImageResource(dataset[position].imageResource)
        Log.d("Adapter",dataset[position].toString())
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    class ItemViewHolder(private val view:View ): RecyclerView.ViewHolder(view){
        val textViewID:TextView=view.findViewById(R.id.tv_student_id)
        val textViewName:TextView=view.findViewById(R.id.tv_name)
        val textViewBloodGroup:TextView=view.findViewById(R.id.tv_blood_group)
        val imageView:ImageView=view.findViewById(R.id.iv_photo)
    }

}