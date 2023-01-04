package com.syedabdullah.roomdbtask.dao

import androidx.room.TypeConverter
import java.util.*

class Converters {

    @TypeConverter
    fun fromTime(value:Long?): Date?{
        return value?.let {
            Date(it)
        }
    }

    @TypeConverter
    fun dateToTime(date: Date?):Long?{
        return date?.time?.toLong()
    }

}