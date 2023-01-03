package com.syedabdullah.roomdbtask.dao

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Room
import androidx.room.RoomDatabase
import com.syedabdullah.roomdbtask.model.Product
import com.syedabdullah.roomdbtask.model.Shop

@androidx.room.Database(entities = [Shop::class,Product::class], version = 3 , exportSchema = false
    )
abstract class ShopDatabase:RoomDatabase() {

    abstract fun ShopDao():ShopDao

    companion object{
        @Volatile
        private var INSTANCE:ShopDatabase?=null

        fun getDatabase(context: Context):ShopDatabase{
            synchronized(this){
                return INSTANCE ?: synchronized(this) {
                    INSTANCE=Room.databaseBuilder(
                        context.applicationContext,
                        ShopDatabase::class.java,
                        "shop_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE!!
                }
            }
        }
    }
}