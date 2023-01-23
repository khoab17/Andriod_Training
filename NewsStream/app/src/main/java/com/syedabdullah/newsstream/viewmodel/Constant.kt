package com.syedabdullah.newsstream.viewmodel

import com.syedabdullah.newsstream.model.Article
import com.syedabdullah.newsstream.model.Bookmark
import com.syedabdullah.newsstream.model.NewsArticle
import java.text.SimpleDateFormat
import java.util.*

class Constant {
    companion object {
        const val TOP_NEWS = "top_news"
        const val GENERAL = "general"
        const val BUSINESS = "business"
        const val ENTERTAINMENT = "entertainment"
        const val SPORTS = "sports"
        const val HEALTH = "health"
        const val TECHNOLOGY = "technology"

        fun bindAllArticleToNewsArticles(
            articles: List<Article>,
            category: String
        ): List<NewsArticle> {
            return articles.map { article ->
                NewsArticle(
                    0,
                    article.author,
                    article.content,
                    article.description,
                    article.publishedAt,
                    article.title,
                    article.url,
                    article.urlToImage,
                    category,
                    false
                )
            }
        }

        fun bindNewsArticleToBookMark(newsArticle: NewsArticle):Bookmark{
            return Bookmark(
                0,
                newsArticle.author,
                newsArticle.content,
                newsArticle.description,
                newsArticle.publishedAt,
                newsArticle.title,
                newsArticle.url,
                newsArticle.urlToImage,
                newsArticle.id,
                newsArticle.category
            )
        }

        fun bindBookmarkToNewsArticle(bookmark: Bookmark):NewsArticle{
            return NewsArticle(
                bookmark.newsId,
                bookmark.author,
                bookmark.content,
                bookmark.description,
                bookmark.publishedAt,
                bookmark.title,
                bookmark.url,
                bookmark.urlToImage,
                bookmark.category,
                true
            )
        }

        fun dateFormat(date: String): String{

            val dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
            if(date.length ==dateFormat.length){
                return ""
            }
            val sdf = SimpleDateFormat(dateFormat, Locale.getDefault())
            val inputTime = sdf.parse(date).time
            val currentTime = System.currentTimeMillis()
            val difference = currentTime - inputTime
            val hoursAgo = (difference / (1000 * 60 * 60)).toInt()
            val daysAgo = (difference / (1000 * 60 * 60*24)).toInt()

            return if (hoursAgo<25){
                ("$hoursAgo hours ago")
            } else{
                ("$daysAgo days ago")
            }
        }
    }
}