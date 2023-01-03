package com.syedabdullah.roomdbtask.repository

import androidx.lifecycle.LiveData
import com.syedabdullah.roomdbtask.dao.ShopDao
import com.syedabdullah.roomdbtask.model.Product
import com.syedabdullah.roomdbtask.model.Shop

class ShopRepository(private val shopDao: ShopDao) {

    fun getShops():LiveData<List<Shop>>{
        return shopDao.getShops()
    }

    suspend fun addShop(shop: Shop)
    {
        shopDao.addShop(shop)
    }

    suspend fun update(shop: Shop){
        shopDao.updateShop(shop)
    }

    suspend fun deleteShop(shop: Shop){
        shopDao.deleteShop(shop)
    }

    suspend fun getProducts(id:Int):List<Product>{
        return shopDao.getProducts(id)
    }

    suspend fun addProduct(product: Product){
        shopDao.addProduct(product)
    }
}