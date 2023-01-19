package com.syedabdullah.newsstream.repository

import androidx.lifecycle.LiveData
import com.syedabdullah.newsstream.dao.NewsDao
import com.syedabdullah.newsstream.model.Bookmark
import com.syedabdullah.newsstream.model.News
import com.syedabdullah.newsstream.model.NewsArticle

class NewsRepository(private val newsDao: NewsDao) {
    //Crud for news_article
    suspend fun addNewsArticle(newsArticle: NewsArticle){
        newsDao.addNewsArticle(newsArticle)
    }

    suspend fun addAllNewsArticles(allNewsArticles:List<NewsArticle>){
        newsDao.addAllNewsArticles(allNewsArticles)
    }

    fun getAllNewsArticle():List<NewsArticle>{
        return newsDao.getAllNewsArticles()
    }

    suspend fun updateNewsArticle(newsArticle: NewsArticle){
        newsDao.updateNewsArticle(newsArticle)
    }

    suspend fun deleteNewsArticle(newsArticle: NewsArticle){
        newsDao.deleteNewsArticle(newsArticle)
    }

    suspend fun deleteAllNewsArticle(){
        newsDao.deleteAllNewsArticle()
    }

    //Crud for bookmark
    suspend fun addBookmark(bookmark: Bookmark){
        newsDao.addBookmark(bookmark)
    }

    fun getAllBookmark():LiveData<List<Bookmark>>{
        return newsDao.getAllBookmarks()
    }

    suspend fun updateBookmark(bookmark: Bookmark){
        newsDao.updateBookmark(bookmark)
    }
    suspend fun deleteBookmark(bookmark: Bookmark){
        newsDao.deleteBookmark(bookmark)
    }

    suspend fun deleteAllBookmarks(){
        newsDao.deleteAllBookmarks()
    }
}