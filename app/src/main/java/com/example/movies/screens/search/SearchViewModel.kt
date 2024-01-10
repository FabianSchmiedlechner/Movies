package com.example.movies.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.MovieRepo
import com.example.movies.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SearchViewModel(private val movieRepo: MovieRepo) :
    ViewModel() {

    private val allMovies: LiveData<List<Movie>> = movieRepo.all
    val filteredMovies: MediatorLiveData<List<Movie>> = MediatorLiveData()

    init {
        filteredMovies.addSource(allMovies) {
            filteredMovies.value = it
        }
    }

    fun favoriteMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepo.updateMovie(movie)
        }
    }

    fun showAllMovies() {
        filteredMovies.value = allMovies.value
    }

    fun filterMovies(query: String) {
        if (query.isNotEmpty())
            filteredMovies.value =
                allMovies.value?.filter { it.title.contains(query, true) } ?: emptyList()
    }
}