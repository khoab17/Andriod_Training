package com.syedabdullah.roomdb.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId:Int,
    @NonNull
    val firstName:String,
    @NonNull
    val lastName:String,
    @NonNull
    val age:Int
)
