package com.example.movies.screens.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movies.MovieAdapter
import com.example.movies.R
import com.example.movies.databinding.FragmentHomeBinding
import com.example.movies.extensions.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModel()
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val favoriteAdapter by lazy { FavoriteAdapter() }
    private val staffPicksAdapter by lazy {
        MovieAdapter { movie ->
            viewModel.favoriteMovie(movie)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnSearch.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
        }

        binding.rvFavorites.adapter = favoriteAdapter
        binding.rvStaffPicks.adapter = staffPicksAdapter

        subscribeUi()
    }

    private fun subscribeUi() {
        viewModel.staffPicks.observe(viewLifecycleOwner) { staffPicks ->
            staffPicksAdapter.submitList(staffPicks)
        }
        viewModel.favorites.observe(viewLifecycleOwner) { favorites ->
            binding.emptyView.visibility = if (favorites.isEmpty()) View.VISIBLE else View.GONE
            favoriteAdapter.submitList(favorites)
        }
    }
}