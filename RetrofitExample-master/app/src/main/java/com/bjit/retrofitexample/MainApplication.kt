package com.bjit.retrofitexample

import android.app.Application
import android.content.Context

class MainApplication : Application() {

    init {
        instance = this
    }
    fun initialize(){
        instance = this
    }

    companion object {
        private var instance: MainApplication? = null

        fun applicationContext() : Context {
           return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
