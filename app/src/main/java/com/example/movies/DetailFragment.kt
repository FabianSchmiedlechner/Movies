package com.example.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movies.data.Movie
import com.example.movies.databinding.FragmentDetailBinding
import com.example.movies.databinding.FragmentSearchBinding
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlin.math.roundToInt

class DetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentDetailBinding.inflate(inflater)

//        val rating = movie.rating.roundToInt()
//        for ((index, star) in binding.llRatingFilled.children.withIndex()) {
//            if (index < rating) star.visibility = View.VISIBLE else star.visibility = View.INVISIBLE
//        }

        binding.btnClose.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }
}