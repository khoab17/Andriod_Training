package com.syedabdullah.roomdbtask.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.syedabdullah.roomdbtask.model.Product
import com.syedabdullah.roomdbtask.model.Shop

@Dao
interface ShopDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShop(shop: Shop)

    @Update
    suspend fun updateShop(shop: Shop)

    @Delete
    suspend fun deleteShop(shop: Shop)

    @Query("SELECT * FROM shop_table")
    fun getShops():LiveData<List<Shop>>

    @Query("SELECT * FROM shop_table WHERE id=:id")
    fun getShop(id:Int):Shop

    @Query("SELECT * FROM product_table WHERE shopId=:id")
    fun getProducts(id:Int):LiveData<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: Product)

    @Update
    suspend fun updateProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)
}