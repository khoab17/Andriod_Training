package com.syedabdullah.newsstream.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.syedabdullah.newsstream.model.Bookmark
import com.syedabdullah.newsstream.model.NewsArticle

@Dao
interface NewsDao {
    //CRUD operation for news_article table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewsArticle(newsArticle: NewsArticle)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAllNewsArticles(allNews:List<NewsArticle>)

    @Query("SELECT * FROM news_article")
    fun getAllNewsArticles():List<NewsArticle>

    @Update
    suspend fun updateNewsArticle(newsArticle: NewsArticle)

    @Delete
    suspend fun deleteNewsArticle(newsArticle: NewsArticle)

    @Query("DELETE FROM news_article")
    suspend fun deleteAllNewsArticle()

    //CRUD operation for bookmark
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmark(bookmark: Bookmark)

    @Query("SELECT * FROM bookmark")
    fun getAllBookmarks():LiveData<List<Bookmark>>

    @Update
    suspend fun updateBookmark(bookmark: Bookmark)

    @Delete
    suspend fun deleteBookmark(bookmark: Bookmark)

    @Query("DELETE FROM bookmark")
    suspend fun deleteAllBookmarks()

}