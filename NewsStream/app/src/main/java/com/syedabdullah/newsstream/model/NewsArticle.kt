package com.syedabdullah.newsstream.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_article")
data class NewsArticle(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source,
    val title: String?,
    val url: String?,
    val urlToImage: String?,
    val type:String
)
