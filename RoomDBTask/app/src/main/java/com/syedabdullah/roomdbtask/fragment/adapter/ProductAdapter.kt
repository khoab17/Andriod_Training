package com.syedabdullah.roomdbtask.fragment.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.syedabdullah.roomdbtask.R
import com.syedabdullah.roomdbtask.fragment.ProductListFragmentDirections
import com.syedabdullah.roomdbtask.model.Product
import com.syedabdullah.roomdbtask.model.Shop
import com.syedabdullah.roomdbtask.viewmodel.ShopViewModel

class ProductAdapter(
    val context: Context,
    val viewModel:ShopViewModel,
    val shop: Shop
): RecyclerView.Adapter<ProductAdapter.ItemViewHolder>() {

    private var listOfProducts= emptyList<Product>()

    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image:ImageView =view.findViewById(R.id.image_view_product)
        val name: TextView =view.findViewById(R.id.tv_name_product)
        val description: TextView =view.findViewById(R.id.tv_description_product)
        val price: TextView =view.findViewById(R.id.tv_price_product)

        val updateButton:Button=view.findViewById(R.id.button_update_product)
        val deleteButton:Button=view.findViewById(R.id.button_delete_Product)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layout= LayoutInflater.from(parent.context).inflate(R.layout.product_view,parent,false)
        return ItemViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.apply {
            image.setImageResource(R.drawable.ic_baseline_shopping_cart_24)
            name.text=listOfProducts[position].name
            description.text=listOfProducts[position].description
            price.text=listOfProducts[position].price.toString()

            updateButton.setOnClickListener { 
                val action = ProductListFragmentDirections.actionProductListFragmentToUpdateProductFragment(listOfProducts[position],shop)
                itemView.findNavController().navigate(action)
            }
            
            deleteButton.setOnClickListener { 
                viewModel.deleteProduct(listOfProducts[position])
                Toast.makeText(context, "product deleted", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return listOfProducts.size
    }

    fun setData(products:List<Product>){
        listOfProducts=products
        Log.d("check", "setData: ${listOfProducts.size}")
        Log.d("check", "setData: ${listOfProducts}")
        notifyDataSetChanged()
    }

}