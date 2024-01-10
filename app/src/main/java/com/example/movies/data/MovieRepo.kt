package com.example.movies.data

import com.example.movies.model.Movie
import timber.log.Timber

class MovieRepo private constructor(
    private val movieDao: MovieDao
) {
    val favorites = movieDao.getFavoritesLive()
    val all = movieDao.getAllLive()
    val staffPicks = movieDao.getStaffPicksLive()

    fun updateMovie(movie: Movie) {
        movieDao.updateMovie(movie)
        Timber.d("Updated movie favorite: %s", movie.favorite)
    }

    fun findMovie(query: String) = movieDao.findMovies(query)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: MovieRepo? = null

        fun getInstance(movieDao: MovieDao) =
            instance ?: synchronized(this) {
                instance ?: MovieRepo(movieDao).also { instance = it }
            }
    }
}