package com.example.movies.data.converters

import androidx.room.TypeConverter
import com.example.movies.model.Director

class DirectorConverter {
    @TypeConverter
    fun fromDirectorToString(value: Director?) : String? =
        value?.let { director -> "${director.name}, ${director.pictureUrl}" }

    @TypeConverter
    fun fromStringToDirector(value: String?) : Director? {
        val strings = value?.split(", ")
        strings?.let {
            return Director(strings[0], strings[1])
        }
        return null
    }

}