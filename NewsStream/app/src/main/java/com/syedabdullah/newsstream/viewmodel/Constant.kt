package com.syedabdullah.newsstream.viewmodel

import com.syedabdullah.newsstream.model.Article
import com.syedabdullah.newsstream.model.Bookmark
import com.syedabdullah.newsstream.model.NewsArticle

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
                newsArticle.id
            )
        }
    }
}