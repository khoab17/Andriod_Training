package com.syedabdullah.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BlogViewModelProviderFactory():ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BlogViewModel::class.java)){
            return BlogViewModel() as T
        }
        throw IllegalAccessException("unknown access exception.")
    }
}