package com.syedabdullah.roomdbtask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.syedabdullah.roomdbtask.dao.ShopDao
import com.syedabdullah.roomdbtask.dao.ShopDatabase
import com.syedabdullah.roomdbtask.model.Product
import com.syedabdullah.roomdbtask.model.Shop
import com.syedabdullah.roomdbtask.repository.ShopRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShopViewModel(application: Application):AndroidViewModel(application) {
    var readAllData:LiveData<List<Shop>>
    lateinit var readAllProduct:LiveData<List<Product>>
    private val repository:ShopRepository

    init {
        val shopDao = ShopDatabase.getDatabase(application).ShopDao()
        repository= ShopRepository(shopDao)
        readAllData = repository.getShops()
    }
    fun addShop(shop: Shop){
        viewModelScope.launch (Dispatchers.IO){
            repository.addShop(shop)
        }
    }
    fun updateShop(shop: Shop){
        viewModelScope.launch (Dispatchers.IO){
            repository.update(shop)
        }
    }
    fun deleteShop(shop: Shop){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteShop(shop)
        }
    }

    //Products viewModel
    fun addProduct(product: Product){
        viewModelScope.launch (Dispatchers.IO){
            repository.addProduct(product)
        }
    }
    fun getProducts(id:Int){
        readAllProduct = repository.getProducts(id)
    }
}