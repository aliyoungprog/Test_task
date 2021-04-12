package com.example.testtask.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import javax.xml.transform.Source

class MovieTypeConvertor {
    @TypeConverter
    fun fromString(source: String?): Source? {
        if (source == null) return null
        val gson = Gson()
        return gson.fromJson(
            source,
            Source::class.java
        )
    }

    @TypeConverter
    fun toString(source: Source?): String? {
        if (source == null) return null
        val gson = Gson()
        return gson.toJson(source)
    }
}