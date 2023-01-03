package com.syedabdullah.roomdbtask.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "shop_table")
data class Shop(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @NonNull
    val name:String,
    @NonNull
    val type:String,
    @NonNull
    val description:String
):Parcelable
