package com.example.movies.screens.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.MovieDao
import com.example.movies.data.MovieRepo
import com.example.movies.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SearchViewModel(private val movieDao: MovieDao, private val movieRepo: MovieRepo) :
    ViewModel() {

    private var allMovies: List<Movie> = emptyList()
    var displayedMovies: MutableLiveData<List<Movie>> = MutableLiveData(emptyList())
    private var filteredMovies: List<Movie> = emptyList()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            allMovies = movieDao.getAll()
            displayedMovies.postValue(allMovies)
        }
    }

    fun favoriteMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepo.updateMovie(movie)
        }
    }

    fun showAllMovies() {
        displayedMovies.value = allMovies
    }

    fun filterMovies(query: String) {
        val filteredMovies = allMovies.filter { it.title.contains(query, true) }
        this.filteredMovies = filteredMovies
        displayedMovies.value = filteredMovies
    }
}