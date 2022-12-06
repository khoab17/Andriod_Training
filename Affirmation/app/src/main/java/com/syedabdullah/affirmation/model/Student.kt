package com.syedabdullah.affirmation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Student(val studentId:Int, val name:String,val bloodGroup:String,@DrawableRes val imageResource:Int,@StringRes val bio:Int) {
}