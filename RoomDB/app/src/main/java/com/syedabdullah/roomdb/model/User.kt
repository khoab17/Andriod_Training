package com.syedabdullah.roomdb.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
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
):Parcelable
