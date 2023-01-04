package com.syedabdullah.roomdbtask.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate
import java.util.*

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
    val description:String,
    @NonNull
    val date: Date
):Parcelable
