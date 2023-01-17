package com.syedabdullah.newsstream.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syedabdullah.newsstream.model.Article
import com.syedabdullah.newsstream.network.NewsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val TAG="check"
class NewsViewModel:ViewModel() {
    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    init {
        getArticlesFromApi()
    }

    private fun getArticlesFromApi() {
        viewModelScope.launch{
            try {
                _articles.value = NewsApi.retrofitService.getTopHeadlineNews().articles
                Log.d(TAG, "getArticlesFromApi: ${articles.value}")
            } catch (e: Exception) {
                _articles.value = listOf()
            }
        }
    }
}