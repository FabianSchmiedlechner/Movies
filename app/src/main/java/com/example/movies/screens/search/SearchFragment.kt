package com.example.movies.screens.search

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movies.MovieAdapter
import com.example.movies.R
import com.example.movies.databinding.FragmentSearchBinding
import com.example.movies.extensions.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel: SearchViewModel by viewModel()
    private val binding by viewBinding(FragmentSearchBinding::bind)
    private val searchAdapter by lazy {
        SearchAdapter { movie ->
            viewModel.favoriteMovie(movie)
        }
    }

    override fun onViewCreated(
        view: View, savedInstanceState: Bundle?
    ) {
        binding.btnClose.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.rvMovies.adapter = searchAdapter

        viewModel.displayedMovies.observe(viewLifecycleOwner) {
            searchAdapter.submitList(it)
        }

        binding.tietSearch.doOnTextChanged { text, _, _, _ ->
            val query = text.toString().lowercase(Locale.getDefault())
            if (query.isNotEmpty()) {
                viewModel.filterMovies(query)
            } else {
                viewModel.showAllMovies()
            }
        }
    }
}