package com.syedabdullah.newsstream.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.syedabdullah.newsstream.model.Bookmark
import com.syedabdullah.newsstream.model.NewsArticle

@Dao
interface NewsDao {
    //CRUD operation for news_article table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNewsArticle(newsArticle: NewsArticle)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAllNewsArticles(allNews:List<NewsArticle>)

    @Query("SELECT * FROM news_article")
    fun getAllNewsArticles():List<NewsArticle>

    @Query("SELECT * FROM news_article WHERE category=:category")
    fun getAllNewsByCategory(category:String):List<NewsArticle>

    @Query("SELECT * FROM news_article WHERE url=:url")
    fun getNewsArticleByUrl(url:String):NewsArticle?

    @Update
    suspend fun updateNewsArticle(newsArticle: NewsArticle)

    @Delete
    suspend fun deleteNewsArticle(newsArticle: NewsArticle)

    @Query("DELETE FROM news_article")
    suspend fun deleteAllNewsArticle()

    //CRUD operation for bookmark
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBookmark(bookmark: Bookmark)

    @Query("SELECT * FROM bookmark ORDER  BY id DESC")
    fun getAllBookmarks():List<Bookmark>

    @Query("SELECT * FROM bookmark WHERE newsId=:newsId")
    fun getBookmarkByNewsId(newsId:Int):Bookmark

    @Update
    suspend fun updateBookmark(bookmark: Bookmark)

    @Delete
    suspend fun deleteBookmark(bookmark: Bookmark)

    @Query("DELETE FROM bookmark")
    suspend fun deleteAllBookmarks()

}