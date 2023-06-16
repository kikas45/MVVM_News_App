package com.example.okayme.db

import androidx.room.TypeConverter
import com.example.okayme.models.Source

class Converters {
    // this `class is used to tell room about the primitive data type
    // hence help us convert ints to string
    // if we have string, then convert to source class

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}