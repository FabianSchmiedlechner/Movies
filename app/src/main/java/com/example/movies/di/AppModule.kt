package com.example.movies.di

import androidx.room.Room
import com.example.movies.data.AppDatabase
import com.example.movies.data.MovieRepo
import com.example.movies.screens.detail.DetailViewModel
import com.example.movies.screens.home.HomeViewModel
import com.example.movies.screens.search.SearchViewModel
import com.example.movies.screens.signup.SignUpViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "movies"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().movieDao() }

    viewModel { HomeViewModel(MovieRepo.getInstance(get())) }
    viewModel { SignUpViewModel(get()) }
    viewModel { SearchViewModel(get(), MovieRepo.getInstance(get())) }
    viewModel { DetailViewModel(get()) }

}