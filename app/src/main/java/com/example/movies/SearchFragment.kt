package com.example.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movies.data.Movie
import com.example.movies.databinding.FragmentSearchBinding
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

class SearchFragment : Fragment() {

    @OptIn(ExperimentalSerializationApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSearchBinding.inflate(inflater)

        binding.btnClose.setOnClickListener {
            findNavController().popBackStack()
        }

        val moviesInputStream = resources.openRawResource(R.raw.movies)
        val movies = Json.decodeFromStream<List<Movie>>(moviesInputStream)


        binding.rvMovies.adapter = MovieAdapter().apply {
            this.submitList(movies)
        }

        return binding.root
    }
}