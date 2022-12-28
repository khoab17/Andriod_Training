package com.syedabdullah.mvvm.adapter

import com.syedabdullah.mvvm.viewmodel.BlogViewModel


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.syedabdullah.mvvm.R
import com.syedabdullah.mvvm.model.Blog

class BlogRecyclerAdapter(
    private val context: Context,
    private val viewModel: BlogViewModel,
    private val arrayList: ArrayList<Blog>
) : RecyclerView.Adapter<BlogRecyclerAdapter.BlogViewHolder>() {

    class BlogViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        val title: TextView = binding.findViewById<TextView>(R.id.title)
        val deleteButton: ImageButton = binding.findViewById<ImageButton>(R.id.delete)
        val date: TextView = binding.findViewById<TextView>(R.id.current_date_textview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return BlogViewHolder(root)
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        val blog = arrayList[position]
        holder.title.text = blog.title
        holder.date.text = blog.date
        holder.deleteButton.setOnClickListener {
            viewModel.removeBlog(blog)
            notifyItemRemoved(arrayList.indexOf(blog))
        }
    }

    override fun getItemCount(): Int {
        if (arrayList.size == 0) {
            Toast.makeText(context, "Blog list is empty", Toast.LENGTH_SHORT).show()
        }
        return arrayList.size
    }
}