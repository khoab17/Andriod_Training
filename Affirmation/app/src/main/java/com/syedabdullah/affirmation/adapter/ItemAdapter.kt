package com.syedabdullah.affirmation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.syedabdullah.affirmation.R
import com.syedabdullah.affirmation.model.Affirmation

class ItemAdapter(private val context:Context ,private val dataset:List<Affirmation>):
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>()

{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout=LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item=dataset[position]
        holder.textView.text=context.getString(item.stringResourceId)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    class ItemViewHolder(private val view:View ): RecyclerView.ViewHolder(view){
        val textView:TextView=view.findViewById(R.id.tv_item_title)
    }

}