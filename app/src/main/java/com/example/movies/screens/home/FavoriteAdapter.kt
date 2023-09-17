package com.example.movies.screens.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movies.model.Movie
import com.example.movies.databinding.ItemMoviePosterBinding

class FavoriteAdapter :
    ListAdapter<Movie, FavoriteAdapter.PosterViewHolder>(MovieDiffCallBack()) {

    class PosterViewHolder(private val binding: ItemMoviePosterBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.ivPoster.load(movie.posterUrl)
            binding.root.setOnClickListener {
                Navigation.findNavController(binding.root)
                    .navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(movie))
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterViewHolder {
        return PosterViewHolder(
            ItemMoviePosterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PosterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}