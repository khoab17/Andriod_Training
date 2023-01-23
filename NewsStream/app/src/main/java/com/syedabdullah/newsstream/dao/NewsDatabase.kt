package com.syedabdullah.newsstream.dao

import android.content.Context
import androidx.room.*
import com.syedabdullah.newsstream.model.Bookmark
import com.syedabdullah.newsstream.model.NewsArticle

@Database(entities = [NewsArticle::class, Bookmark::class], version = 15, exportSchema = false)
abstract class NewsDatabase:RoomDatabase() {

    abstract fun NewsDao():NewsDao

    companion object{
        @Volatile
        private var INSTANCE:NewsDatabase? = null

        fun getDatabase(context: Context):NewsDatabase{
            return INSTANCE ?: synchronized(this){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    "news_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE!!
            }
        }
    }
}