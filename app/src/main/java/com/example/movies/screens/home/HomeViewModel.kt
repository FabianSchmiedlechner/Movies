package com.example.movies.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.model.Movie
import com.example.movies.data.MovieDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

sealed class HomeUiState {
    data class SavedMovies(val savedMovies: List<Movie>, val staffPicks: List<Movie>)
}

class HomeViewModel(private val movieDao: MovieDao) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState.SavedMovies(emptyList(), emptyList()))
    val uiState: StateFlow<HomeUiState.SavedMovies> = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { currentState ->
                val favorites = movieDao.getFavorites()
                val staffPicks = movieDao.getStaffPicks()
                Timber.d("favorites: %s", favorites.size)
                Timber.d("staffPicks: %s", staffPicks.size)
                currentState.copy(
                    savedMovies = favorites,
                    staffPicks = staffPicks
                )
            }
        }
    }

    fun favoriteMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            movieDao.updateMovie(movie.copy(favorite = !movie.favorite))
        }
    }
}