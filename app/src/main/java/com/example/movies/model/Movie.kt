package com.example.movies.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
@Entity
data class Movie(
    var rating: Float,
    @PrimaryKey var id: Int,
    var revenue: Int? = 0,
    var releaseDate: String,
    var director: Director,
    var posterUrl: String,
    @Ignore var cast: List<Actor>,
    var runtime: Int,
    var title: String,
    var overview: String,
    var reviews: Int,
    var budget: Int,
    var language: String,
    @Ignore var genres: List<String>,
    var staffPick: Boolean = false,
    var favorite: Boolean = false,
) : Parcelable {
    constructor(
        rating: Float,
        id: Int,
        revenue: Int?,
        releaseDate: String,
        director: Director,
        posterUrl: String,
        runtime: Int,
        title: String,
        overview: String,
        reviews: Int,
        budget: Int,
        language: String
    ) : this(
        rating = rating,
        id = id,
        revenue = revenue,
        releaseDate = releaseDate,
        director = director,
        posterUrl = posterUrl,
        runtime = runtime,
        title = title,
        overview = overview,
        reviews = reviews,
        budget = budget,
        language = language,
        cast = emptyList(),
        genres = emptyList(),
        staffPick = false,
        favorite = false,
    )
}