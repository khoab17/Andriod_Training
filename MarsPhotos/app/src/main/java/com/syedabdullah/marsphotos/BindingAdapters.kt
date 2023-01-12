package com.syedabdullah.marsphotos

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.syedabdullah.marsphotos.model.MarsPhoto

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MarsPhoto>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("imgUrl")
fun  bindImage(imageView:ImageView,imgUrl: String?){
    imgUrl.let {
        val imgUri = imgUrl!!.toUri().buildUpon().scheme("http").build()

        Glide.with(imageView.context)
            .load(imgUri)
            .into(imageView)
    }
}