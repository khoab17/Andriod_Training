package com.syedabdullah.roomdb.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.syedabdullah.roomdb.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase:RoomDatabase() {
    abstract fun userDao():UserDao

    companion object{
        @Volatile
        private var INSTANCE:UserDatabase?=null

        fun getDatabase(context:Context):UserDatabase{
            val tInstance=INSTANCE
            if(tInstance!=null) return tInstance

            synchronized(this){
                val instance=Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "User_Database"
                ).build()

                INSTANCE=instance
                return instance
            }
        }
    }
}