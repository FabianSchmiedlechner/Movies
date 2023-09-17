package com.example.movies.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movies.data.converters.ActorConverter
import com.example.movies.data.converters.DirectorConverter
import com.example.movies.model.Movie

@Database(entities = [Movie::class], version = 3)
@TypeConverters(DirectorConverter::class, ActorConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}