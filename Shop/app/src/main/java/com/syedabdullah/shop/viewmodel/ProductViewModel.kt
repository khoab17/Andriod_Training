package com.syedabdullah.shop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.syedabdullah.shop.data.DataSource
import com.syedabdullah.shop.model.Product

class ProductViewModel :ViewModel(){
//    private val productList:List<Product> = DataSource().getProducts()
//    val list:MutableLiveData<MutableList<Product>> = productList.toMutableList()

    val list=MutableLiveData<ArrayList<Product>>()
    private val productList= arrayListOf<Product>()

    fun addProduct(product:Product){
        productList.add(product)
        list.value=productList
    }

    fun removeProduct(product:Product){
        productList.remove(product)
        list.value=productList
    }
}