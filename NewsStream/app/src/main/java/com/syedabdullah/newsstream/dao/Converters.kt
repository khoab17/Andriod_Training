package com.syedabdullah.newsstream.dao

import androidx.room.TypeConverters
import com.syedabdullah.newsstream.model.Source

class Converters {
    @TypeConverters
    fun longToSource(source: Source?):String?{
        return source.toString()
    }
    @TypeConverters
    fun fromSource(value:String?):Source?{
        return value?.let {
            return Source()
        }
    }
}