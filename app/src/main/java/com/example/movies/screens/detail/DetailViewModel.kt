package com.example.movies.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.MovieDao
import com.example.movies.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class DetailState(val movie: Movie? = null)

class DetailViewModel(private val movieDao: MovieDao) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailState())
    val uiState: StateFlow<DetailState> = _uiState.asStateFlow()

    fun setMovie(movie: Movie) {
        _uiState.update { currentState ->
            currentState.copy(movie = movie)
        }
    }

    fun favoriteMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            movie.favorite = movie.favorite.not()
            movieDao.updateMovie(movie)
            _uiState.update { currentState ->
                currentState.copy(movie = movie)
            }
        }
    }
}