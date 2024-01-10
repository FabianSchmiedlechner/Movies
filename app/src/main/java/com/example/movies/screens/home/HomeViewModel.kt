package com.example.movies.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.model.Movie
import com.example.movies.data.MovieDao
import com.example.movies.data.MovieRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(private val movieRepo: MovieRepo) : ViewModel() {

    val favorites: LiveData<List<Movie>> = movieRepo.favorites
    val staffPicks: LiveData<List<Movie>> = movieRepo.staffPicks

    fun favoriteMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
//            Timber.d("HomeViewModel favorite: %s", movie.favorite)
            movieRepo.updateMovie(movie)
        }
    }
}