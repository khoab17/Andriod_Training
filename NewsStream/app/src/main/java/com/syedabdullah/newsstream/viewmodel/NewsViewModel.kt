package com.syedabdullah.newsstream.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.syedabdullah.newsstream.dao.NewsDatabase
import com.syedabdullah.newsstream.model.Article
import com.syedabdullah.newsstream.network.NewsApi
import com.syedabdullah.newsstream.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(application: Application):AndroidViewModel(application) {
    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles
    private val repository: NewsRepository

    init {
        val newsDao = NewsDatabase.getDatabase(application).NewsDao()
        repository = NewsRepository(newsDao)
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
            } catch (e: Exception) {
                _articles.value = listOf()
            }
        }
    }

    private fun getArticlesFromApi() {
        viewModelScope.launch{
            try {
                _articles.value = NewsApi.retrofitService.getTopHeadlineNews().articles
                repository.addNewsArticle(ModelBinding.bindArticleToNewsArticle(articles.value?.get(0)!!))
            } catch (e: Exception) {
                _articles.value = listOf()
            }
        }
    }
}