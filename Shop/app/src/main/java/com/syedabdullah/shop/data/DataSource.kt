package com.syedabdullah.shop.data

import com.syedabdullah.shop.model.Product

class DataSource() {
    companion object{
        val listOfProducts= mutableListOf<Product>(
            Product("Apple","Fruit",300.00),
            Product("Orange","Fruit",200.00),
            Product("Spinach","Vegetable",100.00),
            Product("Banana","Fruit",100.00)
        )
    }

    fun getProducts():List<Product>{
        return listOfProducts
    }

    fun addProduct(product: Product){
        listOfProducts.add(product)
    }

    fun removeProduct(product: Product){
        listOfProducts.remove(product)
    }

}