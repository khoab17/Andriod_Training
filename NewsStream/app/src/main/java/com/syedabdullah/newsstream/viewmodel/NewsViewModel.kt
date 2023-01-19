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
import kotlinx.coroutines.withContext

private const val TAG ="view_model"
class NewsViewModel(application: Application):AndroidViewModel(application) {
    private val _articles = MutableLiveData<List<NewsArticle>>()
    val articles: LiveData<List<NewsArticle>> = _articles
    private val repository: NewsRepository

    init {
        val newsDao = NewsDatabase.getDatabase(application).NewsDao()
        repository = NewsRepository(newsDao)

        //_articles.value = repository.getAllNewsArticle()
//        getDataFromRoom()
        //getNewsByCategory(Constant.TOP_NEWS)
//        if(articles.value != null ){
//            if(articles.value!!.isEmpty())
//                getArticlesFromApi()
//        }
    }


    fun getNewsByCategory(category:String){
        viewModelScope.launch(Dispatchers.IO){
            try {
                Log.d(TAG, "getNewsByCategory: ")
                val articles = withContext(Dispatchers.IO) {
                    repository.getAllNewsArticleByCategory(category)
                }
                _articles.postValue(articles)
                Log.d(TAG, "getNewsByCategory: ")
            }
            catch (e:Exception){
                Log.d("view_model", "getDataFromRoom: $e")
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
                fetchNewsByCategory(Constant.GENERAL)
                fetchNewsByCategory(Constant.BUSINESS)
                fetchNewsByCategory(Constant.ENTERTAINMENT)
                fetchNewsByCategory(Constant.SPORTS)

            } catch (e: Exception) {
                _articles.value = listOf()
            }
        }
    }

/*    private fun getDataFromRoom(){
        viewModelScope.launch(Dispatchers.IO) {
           try {
               _articles.postValue(repository.getAllNewsArticle())
           }
           catch (e:Exception){
               Log.d("tag", "getDataFromRoom: $e")
           }
        }
    }*/

    private fun fetchNewsByCategory(category:String){
        viewModelScope.launch {
            val article = NewsApi.retrofitService.getNewsByCategory(category).articles
            val newsArticle =Constant.bindAllArticleToNewsArticles(article,category)
            repository.addAllNewsArticles(newsArticle)
        }
    }
}