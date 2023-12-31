package com.example.movies.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.movies.model.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAll(): List<Movie>

    @Query("SELECT * FROM movie WHERE favorite = 1")
    fun getFavorites(): List<Movie>

    @Query("SELECT * FROM movie WHERE staffPick = 1")
    fun getStaffPicks(): List<Movie>

    @Insert
    fun insertAll(vararg movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<Movie>)

    @Update
    fun updateMovie(movie: Movie)
}