package com.example.movies.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.movies.MovieAdapter
import com.example.movies.R
import com.example.movies.model.Movie
import com.example.movies.databinding.FragmentHomeBinding
import com.example.movies.extensions.viewBinding
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModel()
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val favoriteAdapter by lazy { FavoriteAdapter() }
    private val staffPicksAdapter by lazy { MovieAdapter { movie ->
        viewModel.favoriteMovie(movie)
    } }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnSearch.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
        }

        binding.rvFavorites.adapter = favoriteAdapter
        binding.rvStaffPicks.adapter = staffPicksAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    showFavoriteMovies(uiState.savedMovies)
                    showStaffPicks(uiState.staffPicks)
                }
            }
        }
    }

    override fun onResume() {
        viewModel.updateLists()
        super.onResume()
    }

    private fun showFavoriteMovies(savedMovies: List<Movie>) {
        binding.emptyView.visibility = if (savedMovies.isEmpty()) View.VISIBLE else View.GONE
        favoriteAdapter.submitList(savedMovies)
    }

    private fun showStaffPicks(staffPicks: List<Movie>) {
        staffPicksAdapter.submitList(staffPicks)
    }
}