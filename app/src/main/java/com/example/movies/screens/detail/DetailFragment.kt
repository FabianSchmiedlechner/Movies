package com.example.movies.screens.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.movies.R
import com.example.movies.databinding.FragmentDetailBinding
import com.example.movies.extensions.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val viewModel: DetailViewModel by viewModel()
    private val binding by viewBinding(FragmentDetailBinding::bind)
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val movie = args.movie

        val localDate = LocalDate.parse(movie.releaseDate)

        binding.tvReleaseDate.text = localDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        binding.tvDuration.text = "${(movie.runtime / 60)}h ${movie.runtime % 60}m"
        binding.tvTitle.text = movie.title
        binding.tvYear.text = "(${localDate.year})"
        binding.tvDescription.text = movie.overview
        binding.tvBudget.text = movie.budget.toString()
        binding.tvRevenue.text = movie.revenue.toString()
        binding.tvLanguage.text = movie.language
        binding.tvRating.text = "${movie.rating} (${movie.reviews})"

        binding.ivFavorite.setImageResource(if (movie.favorite) R.drawable.favorite_filled else R.drawable.favorite)
        binding.ivFavorite.setOnClickListener {
            viewModel.favoriteMovie(movie)
            movie.favorite = !movie.favorite
            binding.ivFavorite.setImageResource(if (movie.favorite) R.drawable.favorite_filled else R.drawable.favorite)
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