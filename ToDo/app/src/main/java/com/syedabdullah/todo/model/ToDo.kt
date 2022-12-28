package com.syedabdullah.todo.model


data class ToDo(
    val title:String,
    val description:String,
    val time:String,
    var status:Boolean)