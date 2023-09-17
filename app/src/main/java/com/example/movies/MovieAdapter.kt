package com.example.movies

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movies.data.MovieDao
import com.example.movies.model.Movie
import com.example.movies.databinding.ItemMovieBinding
import com.example.movies.extensions.viewBinding
import com.example.movies.screens.home.HomeFragmentDirections
import com.example.movies.screens.search.SearchFragmentDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt

class MovieAdapter(private val onClick: (Movie) -> Unit) :
    ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffCallBack()) {

    class MovieViewHolder(
        private val binding: ItemMovieBinding,
        private val onClick: (Movie) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.tvYear.text = movie.releaseDate
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
                onClick(movie)
                toggleFavorite(movie)
            }

            binding
        }

        private fun toggleFavorite(movie: Movie) {
            binding.ivBookmark.setImageResource(
                if (movie.favorite) R.drawable.favorite else R.drawable.favorite_filled
            )
            movie.favorite = !movie.favorite
        }
    }

    private class MovieDiffCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(
        binding = parent.viewBinding { inflater ->
            ItemMovieBinding.inflate(
                inflater,
                parent,
                false
            )
        },
        onClick = onClick
    )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}