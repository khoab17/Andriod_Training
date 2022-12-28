package com.syedabdullah.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.syedabdullah.shop.R
import com.syedabdullah.shop.model.Product
import com.syedabdullah.shop.viewmodel.ProductViewModel

class ProductRecyclerViewAdapter(
    private val context: Context,
    private val viewModel: ProductViewModel,
    private val arrayList: ArrayList<Product>
) : RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductViewHolder>() {

    class ProductViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tv_product_name)
        val description: TextView = view.findViewById(R.id.tv_product_description)
        val price: TextView = view.findViewById(R.id.tv_product_price)
        val deleteButton: ImageView = view.findViewById(R.id.image_view_product_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.product_view_item, parent, false)
        return ProductViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = arrayList[position]
        holder.name.text = product.name
        "Description:${product.description}".also { holder.description.text = it }
        "Price:${product.price.toString()}".also { holder.price.text = it }

        holder.deleteButton.setOnClickListener {
            viewModel.removeProduct(arrayList[position])
        }
    }

    override fun getItemCount(): Int {
        if (arrayList.size == 0) Toast.makeText(
            context,
            "product list is empty",
            Toast.LENGTH_SHORT
        ).show()
        return arrayList.size
    }
}