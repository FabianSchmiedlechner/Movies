package com.example.movies.screens.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.movies.R
import com.example.movies.databinding.FragmentDetailBinding
import com.example.movies.extensions.viewBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.roundToInt

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val viewModel: DetailViewModel by viewModel()
    private val binding by viewBinding(FragmentDetailBinding::bind)
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var movie = args.movie

        viewModel.setMovie(movie)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { data ->
                    data.movie?.let { movie = data.movie }
                }
            }
        }

        val localDate = LocalDate.parse(movie.releaseDate)

        binding.tvReleaseDate.text = localDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        binding.tvDuration.text = "${(movie.runtime / 60)}h ${movie.runtime % 60}m"
        binding.tvTitle.text = movie.title
        binding.tvYear.text = "(${localDate.year})"
        binding.tvDescription.text = movie.overview

        val decimalFormat = DecimalFormat("#,###", DecimalFormatSymbols(Locale.GERMANY))
        binding.tvBudget.text = "$ ${decimalFormat.format(movie.budget)}"

        binding.tvRevenue.text = "$ ${decimalFormat.format(movie.revenue)}"
        binding.tvLanguage.text = movie.language
        binding.tvRating.text = "${movie.rating} (${movie.reviews})"

        movie.genres.forEachIndexed { index, genre ->
            when (index) {
                0 -> {
                    binding.tvGenre1.text = genre
                    binding.tvGenre1.visibility = View.VISIBLE
                }
                1 -> {
                    binding.tvGenre2.text = genre
                    binding.tvGenre2.visibility = View.VISIBLE
                }
                2 -> {
                    binding.tvGenre3.text = genre
                    binding.tvGenre3.visibility = View.VISIBLE
                }

            }
        }

        binding.ivFavorite.setImageResource(if (movie.favorite) R.drawable.favorite_filled else R.drawable.favorite)

        binding.ivFavorite.setOnClickListener {
            viewModel.favoriteMovie(movie)
            binding.ivFavorite.setImageResource(if (!movie.favorite) R.drawable.favorite_filled else R.drawable.favorite)
        }

        binding.ivPoster.load(movie.posterUrl)

        val rating = movie.rating.roundToInt()
        for ((index, star) in binding.llRatingFilled.children.withIndex()) {
            if (index < rating) star.visibility = View.VISIBLE else star.visibility = View.INVISIBLE
        }

        binding.btnClose.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}