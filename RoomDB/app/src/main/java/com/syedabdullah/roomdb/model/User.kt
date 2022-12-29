package com.syedabdullah.roomdb.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class User(
    @PrimaryKey
    val userId:Int,
    val firstName:String,
    val lastName:String,
    val age:Int
)
