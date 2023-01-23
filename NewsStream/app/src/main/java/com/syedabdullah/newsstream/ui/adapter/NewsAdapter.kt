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
import com.syedabdullah.newsstream.model.NewsArticle
import com.syedabdullah.newsstream.ui.HomeFragmentDirections
import com.syedabdullah.newsstream.viewmodel.Constant
import com.syedabdullah.newsstream.viewmodel.NewsViewModel

class NewsAdapter(
    private val arrayList: List<NewsArticle>,private val viewModel: NewsViewModel
) : RecyclerView.Adapter<NewsAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.tv_title_news)
        val bookmark:ImageView = view.findViewById(R.id.image_view_bookmark_added)
        val image: ImageView = view.findViewById(R.id.image_view_news)
        val description: TextView = view.findViewById(R.id.tv_description_news)
        val date: TextView = view.findViewById(R.id.tv_date_news)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val root= LayoutInflater.from(parent.context).inflate(R.layout.news_view, parent,false)
        return ItemViewHolder(root)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.title.text = arrayList[position].title
        holder.description.text = arrayList[position].description
        holder.date.text = Constant.dateFormat(arrayList[position].publishedAt.toString())

        if(arrayList[position].urlToImage !=null ){
            Glide.with(holder.itemView.context)
                .load(arrayList[position].urlToImage)
                .override(200,200)
                .centerCrop()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.no_image_available)
                .into(holder.image)
        }
        else{
            Glide.with(holder.itemView.context)
                .load(R.drawable.no_image_available)
                .into(holder.image)
        }

        holder.bookmark.isVisible = arrayList[position].saved

        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToNewsDetailsFragment(arrayList[position])
            holder.itemView.findNavController().navigate(action)
        }

        holder.itemView.setOnLongClickListener {
            viewModel.addOrRemoveBookmark(arrayList[position])
            if(arrayList[position].saved) {
                Snackbar.make(holder.itemView, "News removed from bookmarks!", Snackbar.LENGTH_SHORT).show()
                holder.bookmark.isVisible=false
            }
            else{
                Snackbar.make(holder.itemView,"News Added to bookmarks!",Snackbar.LENGTH_SHORT).show()
                holder.bookmark.isVisible = true
            }
            true
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}