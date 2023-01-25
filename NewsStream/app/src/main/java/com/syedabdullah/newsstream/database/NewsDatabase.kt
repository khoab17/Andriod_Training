package com.syedabdullah.newsstream.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
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