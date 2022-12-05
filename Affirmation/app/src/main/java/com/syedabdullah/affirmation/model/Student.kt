package com.syedabdullah.affirmation.model

import androidx.annotation.DrawableRes

data class Student(val studentId:Int, val name:String,val bloodGroup:String,@DrawableRes val imageResource:Int) {
}