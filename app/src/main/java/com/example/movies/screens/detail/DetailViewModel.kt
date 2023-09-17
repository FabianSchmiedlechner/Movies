package com.example.movies.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.MovieDao
import com.example.movies.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val movieDao: MovieDao) : ViewModel() {
    fun favoriteMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            movieDao.updateMovie(movie.copy(favorite = !movie.favorite))
        }
    }
}