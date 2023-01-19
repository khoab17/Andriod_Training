package com.syedabdullah.newsstream.viewmodel


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.syedabdullah.newsstream.dao.NewsDatabase
import com.syedabdullah.newsstream.model.Article
import com.syedabdullah.newsstream.model.NewsArticle
import com.syedabdullah.newsstream.network.NewsApi
import com.syedabdullah.newsstream.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(application: Application):AndroidViewModel(application) {
    private val _articles = MutableLiveData<List<NewsArticle>>()
    val articles: LiveData<List<NewsArticle>> = _articles
    private val repository: NewsRepository

    init {
        val newsDao = NewsDatabase.getDatabase(application).NewsDao()
        repository = NewsRepository(newsDao)

        //_articles.value = repository.getAllNewsArticle()
        getDataFromRoom()
        Log.d("tag", "data: ${articles.value}")
    }


    fun getNewByCategory(category:String){
        viewModelScope.launch{
            try {
               // _articles.value = NewsApi.retrofitService.getNewsByCategory(category).articles
            } catch (e: Exception) {
                _articles.value = listOf()
            }
        }
    }

    private fun getArticlesFromApi() {
        viewModelScope.launch{
            try {
                repository.deleteAllNewsArticle()
                val temp = NewsApi.retrofitService.getTopHeadlineNews().articles
                val temp2 = Constant.bindAllArticleToNewsArticles(temp, Constant.TOP_NEWS)
                repository.addAllNewsArticles(temp2)
            } catch (e: Exception) {
                _articles.value = listOf()
            }
        }
    }

    private fun getDataFromRoom(){
        viewModelScope.launch(Dispatchers.IO) {
           try {
               _articles.postValue(repository.getAllNewsArticle())
           }
           catch (e:Exception){
               Log.d("tag", "getDataFromRoom: $e")
           }
        }
    }
}