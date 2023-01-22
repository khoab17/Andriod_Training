package com.syedabdullah.newsstream.viewmodel


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.syedabdullah.newsstream.dao.NewsDatabase
import com.syedabdullah.newsstream.model.Bookmark
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
    private val _bookmarks = MutableLiveData<List<Bookmark>>()
    val bookmarks:LiveData<List<Bookmark>> =_bookmarks
    private val repository: NewsRepository

    init {
        val newsDao = NewsDatabase.getDatabase(application).NewsDao()
        repository = NewsRepository(newsDao)
        Log.d(TAG, "init calling: ")
    }


    //APIs data fetching==================================================
    fun fetchApiNewsByCategory(category:String){
        viewModelScope.launch {
            try {
                if(category == Constant.TOP_NEWS)
                {
                    val articles = NewsApi.retrofitService.getTopHeadlineNews().articles
                    val newsArticle = Constant.bindAllArticleToNewsArticles(articles, Constant.TOP_NEWS)
                    repository.addAllNewsArticles(newsArticle)
                    _articles.postValue(repository.getAllNewsArticleByCategory(Constant.TOP_NEWS))
                }
                else{
                    val article = NewsApi.retrofitService.getNewsByCategory(category).articles
                    val newsArticle =Constant.bindAllArticleToNewsArticles(article,category)
                    repository.addAllNewsArticles(newsArticle)
                    _articles.postValue(repository.getAllNewsArticleByCategory(category))
                }
            }
            catch (e:Exception) {
                Log.d(TAG, "fetchApiNewsByCategory: $e")
            }
        }
    }

    //Getting data's from ROOM db============================================
    fun getNewsByCategory(category:String){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val articles = withContext(Dispatchers.IO) {
                    repository.getAllNewsArticleByCategory(category)
                }
                _articles.postValue(articles)
            }
            catch (e:Exception){
                Log.d("view_model", "getDataFromRoom: $e")
            }
        }
    }

    fun getBookmarks(){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val bookmarks = withContext(Dispatchers.IO) {
                    repository.getAllBookmark()
                }
                _bookmarks.postValue(bookmarks)
           }
           catch (e:Exception){
               Log.d(TAG, "getBookmarks Exception: $e")
           }
        }
    }

    fun addOrRemoveBookmark(newsArticle: NewsArticle){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if(newsArticle.saved){
                    val bookmark = repository.getBookmarkByNewsId(newsArticle.id)
                    repository.deleteBookmark(bookmark)
                    newsArticle.saved = false
                    repository.updateNewsArticle(newsArticle)
                }
                else{
                    val bookmark = Constant.bindNewsArticleToBookMark(newsArticle)
                    repository.addBookmark(bookmark)
                    newsArticle.saved = true
                    repository.updateNewsArticle(newsArticle)
                }
                _bookmarks.postValue(repository.getAllBookmark())
            }
            catch (_:java.lang.Exception){}
        }
    }

//    fun searchNews(text:String){
//        for(i in articles){
//
//        }
//    }
}