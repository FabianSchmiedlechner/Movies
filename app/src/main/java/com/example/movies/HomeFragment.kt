package com.example.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movies.data.Movie
import com.example.movies.databinding.FragmentHomeBinding
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream


class HomeFragment : Fragment() {

    @OptIn(ExperimentalSerializationApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater)

        binding.btnSearch.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
        }

        val moviesInputStream = resources.openRawResource(R.raw.movies)
        val movies = Json.decodeFromStream<List<Movie>>(moviesInputStream)

        val staffPicksInputStream = resources.openRawResource(R.raw.staff_picks)
        val staffPicks = Json.decodeFromStream<List<Movie>>(staffPicksInputStream)

        binding.rvStaffPicks.adapter = MovieAdapter().apply {
            this.submitList(staffPicks)
        }

        return binding.root
    }
}