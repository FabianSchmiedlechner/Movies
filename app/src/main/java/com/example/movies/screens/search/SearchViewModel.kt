package com.example.movies.screens.search

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

sealed class SearchUiState {
    data class Default(
        val allMovies: List<Movie>,
        val displayedMovies: List<Movie>,
        val filteredMovies: List<Movie>
    )
}

class SearchViewModel(private val movieDao: MovieDao) : ViewModel() {

    private val _uiState =
        MutableStateFlow(SearchUiState.Default(emptyList(), emptyList(), emptyList()))
    val uiState: StateFlow<SearchUiState.Default> = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { currentState ->
                val allMovies = movieDao.getAll()
                currentState.copy(
                    allMovies = allMovies,
                    displayedMovies = allMovies
                )
            }
        }
    }

    fun favoriteMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            movieDao.updateMovie(movie.copy(favorite = !movie.favorite))
        }
    }

    fun showAllMovies() {
        _uiState.update { currentState ->
            currentState.copy(
                displayedMovies = currentState.allMovies
            )
        }
    }

    fun filterMovies(query: String) {
        val filteredMovies = uiState.value.allMovies.filter { it.title.contains(query, true) }
        _uiState.update { currentState ->
            currentState.copy(
                filteredMovies = filteredMovies,
                displayedMovies = filteredMovies
            )
        }
    }
}