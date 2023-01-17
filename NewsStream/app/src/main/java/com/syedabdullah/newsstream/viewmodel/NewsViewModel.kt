package com.syedabdullah.newsstream.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syedabdullah.newsstream.model.Article
import com.syedabdullah.newsstream.network.NewsApi
import kotlinx.coroutines.launch

class NewsViewModel:ViewModel() {
    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    init {
        getArticlesFromApi()
    }

    fun getNewByCountry(country:String){
        viewModelScope.launch{
            try {
                _articles.value = NewsApi.retrofitService.getNewsByCountry(country).articles
            } catch (e: Exception) {
                _articles.value = listOf()
            }
        }
    }

    fun getNewByCategory(category:String){
        viewModelScope.launch{
            try {
                _articles.value = NewsApi.retrofitService.getNewsByCountry(category).articles
                Log.d("TAG", "getNewByCategory: ${articles.value}")
            } catch (e: Exception) {
                _articles.value = listOf()
            }
        }
    }

    private fun getArticlesFromApi() {
        viewModelScope.launch{
            try {
                _articles.value = NewsApi.retrofitService.getTopHeadlineNews().articles
            } catch (e: Exception) {
                _articles.value = listOf()
            }
        }
    }
}