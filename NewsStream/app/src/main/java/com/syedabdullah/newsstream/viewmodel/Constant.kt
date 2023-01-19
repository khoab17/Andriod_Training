package com.syedabdullah.newsstream.viewmodel

import com.syedabdullah.newsstream.model.Article
import com.syedabdullah.newsstream.model.NewsArticle

class Constant {
    companion object {
        const val TOP_NEWS = "top_news"
        const val BUSINESS = "business"
        const val SPORTS = "sports"

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
                    article.source,
                    article.title,
                    article.url,
                    article.urlToImage,
                    category,
                    false
                )
            }
        }
    }
}