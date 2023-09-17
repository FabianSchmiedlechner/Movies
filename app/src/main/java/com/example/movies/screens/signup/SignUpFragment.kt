package com.example.movies.screens.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movies.R
import com.example.movies.databinding.FragmentSignUpBinding
import com.example.movies.extensions.viewBinding
import com.example.movies.model.Movie
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val viewModel: SignUpViewModel by viewModel()
    private val binding by viewBinding(FragmentSignUpBinding::bind)

    override fun onViewCreated(
        view: View, savedInstanceState: Bundle?
    ) {
        binding.btnSignUp.setOnClickListener {
            viewModel.initDb(parseMovies(), parseStaffPicks())
            findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToHomeFragment())
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun parseStaffPicks(): List<Movie> {
        val staffPicksInputStream = resources.openRawResource(R.raw.staff_picks)
        val staffPicks = Json.decodeFromStream<List<Movie>>(staffPicksInputStream)
        staffPicks.forEach { it.staffPick = true }
        return staffPicks
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun parseMovies(): List<Movie> {
        val moviesInputStream = resources.openRawResource(R.raw.movies)
        return Json.decodeFromStream(moviesInputStream)
    }
}