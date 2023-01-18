package com.syedabdullah.newsstream.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.syedabdullah.newsstream.model.Bookmark
import com.syedabdullah.newsstream.model.NewsArticle

interface NewsDao {
    //CRUD operation for news_article table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewsArticle(newsArticle: NewsArticle)

    @Query("SELECT * FROM news_article")
    fun getAllNewsArticles():LiveData<List<NewsArticle>>

    @Update
    suspend fun updateNewsArticle(newsArticle: NewsArticle)

    @Delete
    suspend fun deleteNewsArticle(newsArticle: NewsArticle)

    //CRUD operation for bookmark
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmark(bookmark: Bookmark)

    @Query("SELECT * FROM bookmark")
    fun getAllBookmarks():LiveData<List<Bookmark>>

    @Update
    suspend fun updateBookmark(bookmark: Bookmark)

    @Delete
    suspend fun deleteBookmark(bookmark: Bookmark)

}