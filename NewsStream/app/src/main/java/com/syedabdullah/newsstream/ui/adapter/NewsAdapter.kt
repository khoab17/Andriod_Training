package com.syedabdullah.newsstream.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.syedabdullah.newsstream.R
import com.syedabdullah.newsstream.model.Article

class NewsAdapter(
    private val arrayList: List<Article>
) : RecyclerView.Adapter<NewsAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.tv_title)
        val image: ImageView = view.findViewById(R.id.image_view_image)
        val description: TextView = view.findViewById(R.id.tv_description)
        val date: TextView = view.findViewById(R.id.tv_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val root= LayoutInflater.from(parent.context).inflate(R.layout.news_view, parent,false)
        return ItemViewHolder(root)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        Log.d("Check", "onBindViewHolder: ${arrayList[position].title}")
        holder.title.text = arrayList[position].title
        holder.description.text = arrayList[position].description
        holder.date.text = arrayList[position].publishedAt

        Glide.with(holder.itemView.context)
            .load(arrayList[position].urlToImage)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}