package com.example.movies.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable @Parcelize
data class Movie(
    val rating: Float,
    val id: Int,
    val revenue: Int? = 0,
    val releaseDate: String,
    val director: Director,
    val posterUrl: String,
    val cast: List<Actor>,
    val runtime: Int,
    val title: String,
    val overview: String,
    val reviews: Int,
    val budget: Int,
    val language: String,
    val genres: List<String>,
    ) : Parcelable
