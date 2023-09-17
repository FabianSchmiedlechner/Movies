package com.example.movies.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
@Entity
data class Director(
    @PrimaryKey val name: String,
    val pictureUrl: String,
) : Parcelable
