package com.syedabdullah.newsstream.workers

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.syedabdullah.newsstream.dao.NewsDatabase
import com.syedabdullah.newsstream.model.Article
import com.syedabdullah.newsstream.model.NewsArticle
import com.syedabdullah.newsstream.network.ApiConstant
import com.syedabdullah.newsstream.network.NewsApi
import com.syedabdullah.newsstream.repository.NewsRepository
import com.syedabdullah.newsstream.viewmodel.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApiWorker(private val context: Context, private val workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {
    private val _articles = MutableLiveData<List<NewsArticle>>()
    private lateinit var repository:NewsRepository
    private val articles: LiveData<List<NewsArticle>> = _articles
    init {
        val newsDao = NewsDatabase.getDatabase(Application()).NewsDao()
        repository = NewsRepository(newsDao)
    }
    override suspend fun doWork(): Result {
        withContext(Dispatchers.IO){
            val articles = NewsApi.retrofitService.getTopHeadlineNews().articles
            val newsArticle = Constant.bindAllArticleToNewsArticles(articles, Constant.TOP_NEWS)
            repository.addAllNewsArticles(newsArticle)
        }
        return Result.success()
    }
}