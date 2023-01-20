package com.syedabdullah.newsstream.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.syedabdullah.newsstream.R
import com.syedabdullah.newsstream.model.Bookmark
import com.syedabdullah.newsstream.viewmodel.NewsViewModel
private const val TAG = "b_adapter"
class BookmarkAdapter(private val bookmarks: List<Bookmark>, private val viewModel:NewsViewModel)
    :RecyclerView.Adapter<BookmarkAdapter.ItemViewHolder>() {

    init {
        Log.d(TAG, "Bookmark Adapter: ${bookmarks[0]} ")
    }

    class ItemViewHolder(view:View):RecyclerView.ViewHolder(view){
        val title: TextView = view.findViewById(R.id.tv_title_bookmark)
        val image: ImageView = view.findViewById(R.id.image_view_image_bookmark)
        val description: TextView = view.findViewById(R.id.tv_description_bookmark)
        val date: TextView = view.findViewById(R.id.tv_date_bookmark)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val root= LayoutInflater.from(parent.context).inflate(R.layout.bookmark_view, parent,false)
        return ItemViewHolder(root)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.title.text = bookmarks[position].title
        holder.description.text = bookmarks[position].description
        holder.date.text = bookmarks[position].publishedAt

        Glide.with(holder.itemView.context)
            .load(bookmarks[position].urlToImage)
            .placeholder(R.drawable.loading_animation)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return bookmarks.size
    }
}