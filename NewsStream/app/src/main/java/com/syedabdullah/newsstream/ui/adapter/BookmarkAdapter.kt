package com.syedabdullah.newsstream.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.syedabdullah.newsstream.R
import com.syedabdullah.newsstream.model.Bookmark
import com.syedabdullah.newsstream.ui.BookmarkFragmentDirections
import com.syedabdullah.newsstream.viewmodel.Constant
import com.syedabdullah.newsstream.viewmodel.NewsViewModel

class BookmarkAdapter(private val bookmarks: List<Bookmark>, private val viewModel:NewsViewModel)
    :RecyclerView.Adapter<BookmarkAdapter.ItemViewHolder>() {

    class ItemViewHolder(view:View):RecyclerView.ViewHolder(view){
        val title: TextView = view.findViewById(R.id.tv_title_bookmark)
        val image: ImageView = view.findViewById(R.id.image_view_bookmark)
        val description: TextView = view.findViewById(R.id.tv_description_bookmark)
        val saved:ImageView = view.findViewById(R.id.image_view_bookmark_added)
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
        holder.saved.isVisible = true
        Glide.with(holder.itemView.context)
            .load(bookmarks[position].urlToImage)
            .placeholder(R.drawable.loading_animation).centerCrop()
            .into(holder.image)

        holder.itemView.setOnClickListener {
            val action = BookmarkFragmentDirections.actionBookmarkFragmentToNewsDetailsFragment(Constant.bindBookmarkToNewsArticle(bookmark = bookmarks[position]))
            holder.itemView.findNavController().navigate(action)
        }

        holder.itemView.setOnLongClickListener {
            viewModel.addOrRemoveBookmark(Constant.bindBookmarkToNewsArticle(bookmarks[position]))
            Snackbar.make(holder.itemView,"News removed from bookmarks!", Snackbar.LENGTH_SHORT).show()
            true
        }
    }

    override fun getItemCount(): Int {
        return bookmarks.size
    }
}