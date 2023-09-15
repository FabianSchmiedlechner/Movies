package com.example.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movies.data.Movie
import com.example.movies.databinding.ItemMovieBinding
import kotlin.math.roundToInt

class MovieAdapter :
    ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffCallBack()) {

    class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.tvYear.text = movie.releaseDate
            binding.tvTitle.text = movie.title
            binding.ivPoster.load(movie.posterUrl)

            val rating = movie.rating.roundToInt()
            for ((index, star) in binding.llRatingFilled.children.withIndex()) {
                if (index < rating) star.visibility = View.VISIBLE else star.visibility = View.INVISIBLE
            }

            binding.root.setOnClickListener {
                Navigation.findNavController(binding.root).navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(movie))
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}