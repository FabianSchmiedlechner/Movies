package com.example.movies.screens.search

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.movies.MovieAdapter
import com.example.movies.R
import com.example.movies.model.Movie
import com.example.movies.databinding.FragmentSearchBinding
import com.example.movies.extensions.viewBinding
import com.example.movies.screens.home.HomeUiState
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel: SearchViewModel by viewModel()
    private val binding by viewBinding(FragmentSearchBinding::bind)
    private lateinit var adapter: MovieAdapter
//    private val adapter by inject<MovieAdapter>()

    override fun onViewCreated(
        view: View, savedInstanceState: Bundle?
    ) {
        binding.btnClose.setOnClickListener {
            findNavController().popBackStack()
        }

        adapter = MovieAdapter { movie ->
            viewModel.favoriteMovie(movie)
        }
        binding.rvMovies.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    showMovies(uiState.displayedMovies)
                }
            }
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

    private fun showMovies(movies: List<Movie>) {
        adapter.submitList(movies)
    }

//    private fun filterWithQuery(query: String) {
//        // Perform operation only if query is not empty
//        if (query.isNotEmpty()) {
//            // Call the function with valid query and take new filtered list.
//            val filteredList: List<Movie> = viewModel.filterMovies(query)
//            // Call the adapter with new filtered array list
//            adapter.submitList(filteredList)
//            // If the matches are empty hide RecyclerView and show error text
////            toggleRecyclerView(filteredList)
//        } else if (query.isEmpty()) {
//            // If query is empty don't make changes to list
////            attachAdapter(sportsList)
//        }
//    }
}