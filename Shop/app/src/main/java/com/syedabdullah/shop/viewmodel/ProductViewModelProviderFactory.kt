package com.syedabdullah.shop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProductViewModelProviderFactory:ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProductViewModel::class.java))
            return ProductViewModel() as T
        else{
            throw IllegalAccessException("from product factory provider.")
        }
    }
}