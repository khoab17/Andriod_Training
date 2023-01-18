package com.syedabdullah.newsstream.viewmodel

import com.syedabdullah.newsstream.model.Article
import com.syedabdullah.newsstream.model.NewsArticle

class ModelBinding {
    companion object{
        fun bindArticleToNewsArticle(article: Article): NewsArticle {
            return NewsArticle(
                0,
                article.author,
                article.content,
                article.description,
                article.publishedAt,
                article.source,
                article.title,
                article.url,
                article.urlToImage
            )
        }
    }
}