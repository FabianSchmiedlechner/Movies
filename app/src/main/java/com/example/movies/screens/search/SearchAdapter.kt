package com.example.movies.screens.search

import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movies.R
import com.example.movies.data.MovieRepo
import com.example.movies.databinding.ItemMovieBinding
import com.example.movies.extensions.viewBinding
import com.example.movies.model.Movie
import com.example.movies.screens.home.HomeFragmentDirections
import com.example.movies.screens.search.SearchFragmentDirections
import timber.log.Timber
import java.time.LocalDate
import kotlin.math.roundToInt

class SearchAdapter(private val onClick: (Movie) -> Unit) :
    ListAdapter<Movie, SearchAdapter.SearchViewHolder>(MovieDiffCallBack()) {

    class SearchViewHolder(
        private val binding: ItemMovieBinding,
        private val onClick: (Movie) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {

            val localDate = LocalDate.parse(movie.releaseDate)

            binding.tvYear.text = localDate.year.toString()
            binding.tvTitle.text = movie.title
            binding.ivPoster.load(movie.posterUrl)

            val rating = movie.rating.roundToInt()
            for ((index, star) in binding.llRatingFilled.children.withIndex()) {
                if (index < rating) star.visibility = View.VISIBLE else star.visibility =
                    View.INVISIBLE
            }

            binding.root.setOnClickListener {
                if (Navigation.findNavController(binding.root).currentDestination?.label == "fragment_home") {
                    Navigation.findNavController(binding.root)
                        .navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(movie))
                } else {
                    Navigation.findNavController(binding.root).navigate(
                        SearchFragmentDirections.actionSearchFragmentToDetailFragment(movie)
                    )
                }
            }

            binding.ivBookmark.setImageResource(if (movie.favorite) R.drawable.favorite_filled else R.drawable.favorite)

            binding.ivBookmark.setOnClickListener {
                val toggled = movie.copy(favorite = !movie.favorite)
                onClick(toggled) // movieRepo.updateMovie(movie)
//                binding.ivBookmark.setImageResource(if (toggled.favorite) R.drawable.favorite_filled else R.drawable.favorite)
            }
            binding
        }
    }

    private class MovieDiffCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SearchViewHolder(
        binding = parent.viewBinding { inflater ->
            ItemMovieBinding.inflate(
                inflater,
                parent,
                false
            )
        },
        onClick = onClick
    )

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}