package com.syedabdullah.roomdbtask.dao

import android.content.Context
import androidx.room.*
import com.syedabdullah.roomdbtask.model.Product
import com.syedabdullah.roomdbtask.model.Shop

@TypeConverters(Converters::class)
@Database(entities = [Shop::class,Product::class], version = 4 , exportSchema = false
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