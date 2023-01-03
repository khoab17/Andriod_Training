package com.syedabdullah.roomdbtask.fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.syedabdullah.roomdbtask.R
import com.syedabdullah.roomdbtask.fragment.HomeFragment
import com.syedabdullah.roomdbtask.fragment.HomeFragmentDirections
import com.syedabdullah.roomdbtask.model.Shop
import com.syedabdullah.roomdbtask.viewmodel.ShopViewModel

class ShopAdapter(
    val context: Context,
    private val viewModel: ShopViewModel
): RecyclerView.Adapter<ShopAdapter.ItemViewHolder>() {

    private var listOfShops= emptyList<Shop>()

    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image:ImageView =view.findViewById(R.id.image_view_shop)
        val name: TextView =view.findViewById(R.id.tv_name_shop)
        val type: TextView =view.findViewById(R.id.tv_type_shop)
        val description: TextView =view.findViewById(R.id.tv_description)

        val updateButton: Button =view.findViewById(R.id.button_update)
        val deleteButton: Button =view.findViewById(R.id.button_delete)
        val viewProduct: Button =view.findViewById(R.id.button_view_products)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layout= LayoutInflater.from(parent.context).inflate(R.layout.shop_view_item,parent,false)
        return ItemViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.apply {
            image.setImageResource(R.drawable.ic_baseline_shop_24)
            name.text=listOfShops[position].name
            type.text=listOfShops[position].type
            description.text=listOfShops[position].description

            updateButton.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToUpdateShopFragment(listOfShops[position])
                holder.itemView.findNavController().navigate(action)
            }

            deleteButton.setOnClickListener {
                viewModel.deleteShop(listOfShops[position])
            }

            viewProduct.setOnClickListener {
                val action =HomeFragmentDirections.actionHomeFragmentToProductListFragment(listOfShops[position])
                holder.itemView.findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount(): Int {
        return listOfShops.size
    }

    fun setData(shops:List<Shop>){
        listOfShops=shops
        notifyDataSetChanged()
    }
}