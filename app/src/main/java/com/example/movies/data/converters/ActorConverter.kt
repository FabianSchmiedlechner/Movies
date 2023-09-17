package com.example.movies.data.converters

import androidx.room.TypeConverter
import com.example.movies.model.Actor

class ActorConverter {

    @TypeConverter
    fun fromActorToString(value: Actor?) : String? =
        value?.let { actor -> "${actor.name}, ${actor.pictureUrl}, ${actor.character}"}

    @TypeConverter
    fun fromStringToActor(value: String?) : Actor? {
        val strings = value?.split(", ")
        strings?.let {
            return Actor(strings[0], strings[1], strings[2])
        }
        return null
    }
}