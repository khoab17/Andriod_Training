package com.syedabdullah.fragmentnavigation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val userId:Int,
    val userName:String,
    val email:String):Parcelable