package com.syedabdullah.newsstream.database

import androidx.room.TypeConverter
import com.syedabdullah.newsstream.model.Source

class Converters {
    @TypeConverter
    fun longToSource(source: Source?):String?{
        if(source!=null){
            return "${source.id}:${source.name}"
        }
        return null
    }
    @TypeConverter
    fun fromString(value: String?): Source? {
        if (value == null) {
            return null
        }
        val parts = value.split(":")
        return Source(parts[0], parts[1])
    }
}