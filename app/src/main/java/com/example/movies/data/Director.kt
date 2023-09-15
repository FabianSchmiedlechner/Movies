package com.example.movies.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable @Parcelize
data class Director(
    val name: String,
    val pictureUrl: String,
) : Parcelable
