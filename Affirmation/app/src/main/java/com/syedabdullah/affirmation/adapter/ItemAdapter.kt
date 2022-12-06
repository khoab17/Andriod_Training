package com.syedabdullah.affirmation.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.syedabdullah.affirmation.R
import com.syedabdullah.affirmation.StudentsDetailsActivity
import com.syedabdullah.affirmation.model.Student

class ItemAdapter(private val context:Context ,private val dataset:List<Student>):
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>()

{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        //Log.d("Demo",(10).toString())
        val adapterLayout=LayoutInflater.from(parent.context).inflate(R.layout.list_student,parent,false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.textViewID.text=context.getString(R.string.student_id,dataset[position].studentId.toString())
        holder.textViewName.text=context.getString(R.string.student_name,dataset[position].name)
        holder.textViewBloodGroup.text=context.getString(R.string.student_blood_group,dataset[position].bloodGroup)
        holder.imageView.setImageResource(dataset[position].imageResource)
        Log.d("Adapter",dataset[position].toString())

        holder.imageView.setOnClickListener{
            val intent= Intent(context,StudentsDetailsActivity::class.java)
            intent.putExtra("student_id",dataset[position].studentId.toString())
            intent.putExtra("student_name",dataset[position].name)
            intent.putExtra("student_blood_group",dataset[position].bloodGroup)
            intent.putExtra("image_resource",(dataset[position].imageResource).toString())
            intent.putExtra("bio",dataset[position].bio)
            Log.d("test",dataset[position].studentId.toString())
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    class ItemViewHolder(private val view:View ): RecyclerView.ViewHolder(view){
        val textViewID:TextView=view.findViewById(R.id.tv_student_id)
        val textViewName:TextView=view.findViewById(R.id.tv_name)
        val textViewBloodGroup:TextView=view.findViewById(R.id.tv_blood_group)
        val imageView:ImageView=view.findViewById(R.id.iv_photo)
        val bio:TextView=view.findViewById(R.id.tv_bio)
    }

}