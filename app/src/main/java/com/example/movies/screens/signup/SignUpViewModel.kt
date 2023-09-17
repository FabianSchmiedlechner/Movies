package com.example.movies.screens.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.MovieDao
import com.example.movies.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel(private val movieDao: MovieDao) : ViewModel() {

    fun initDb(movies: List<Movie>) {
        viewModelScope.launch(Dispatchers.IO) {
            movieDao.insertAll(movies)
        }
    }
}