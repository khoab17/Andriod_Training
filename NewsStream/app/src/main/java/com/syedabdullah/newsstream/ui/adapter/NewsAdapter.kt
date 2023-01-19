package com.syedabdullah.newsstream.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.syedabdullah.newsstream.R
import com.syedabdullah.newsstream.model.Article
import com.syedabdullah.newsstream.model.NewsArticle
import com.syedabdullah.newsstream.ui.HomeFragment
import com.syedabdullah.newsstream.ui.HomeFragmentDirections
import com.syedabdullah.newsstream.ui.NewsFeedFragment
import com.syedabdullah.newsstream.ui.NewsFeedFragmentDirections
import com.syedabdullah.newsstream.viewmodel.NewsViewModel

class NewsAdapter(
    private val arrayList: List<NewsArticle>,private val viewModel: NewsViewModel
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
        holder.title.text = arrayList[position].title
        holder.description.text = arrayList[position].description
        holder.date.text = arrayList[position].publishedAt

        Glide.with(holder.itemView.context)
            .load(arrayList[position].urlToImage)
            .placeholder(R.drawable.loading_animation)
            .into(holder.image)

        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToNewsDetailsFragment(arrayList[position].url!!)
            holder.itemView.findNavController().navigate(action)
        }

        holder.itemView.setOnLongClickListener {
           // Toast.makeText(holder.itemView.context, "News Added to bookmarks", Toast.LENGTH_SHORT).show()
            viewModel.addOrRemoveBookmark(arrayList[position])
            if(arrayList[position].saved)
                Snackbar.make(holder.itemView,"News removed to bookmarks!",Snackbar.LENGTH_SHORT).show()
            Snackbar.make(holder.itemView,"News Added to bookmarks!",Snackbar.LENGTH_SHORT).show()
            true
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}