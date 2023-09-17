package com.example.movies.data.converters

import androidx.room.TypeConverter
import com.example.movies.model.Director
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class GenreConverter {
    @TypeConverter
    fun fromListToString(value: List<String>?) : String? =
        value?.let { genre -> Json.encodeToString(genre) }

    @TypeConverter
    fun fromStringToList(value: String?) : List<String> {
        return Json.decodeFromString(value ?: "")
    }

}