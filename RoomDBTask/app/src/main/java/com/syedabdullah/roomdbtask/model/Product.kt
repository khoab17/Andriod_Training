package com.syedabdullah.roomdbtask.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "product_table", foreignKeys = [ForeignKey (
    entity = Shop::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("shopId"),
    onDelete = ForeignKey.CASCADE
        )])
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
    val description:String,
    val price:Double,
    val shopId:Int
):Parcelable
