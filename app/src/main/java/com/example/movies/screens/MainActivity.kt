package com.example.movies.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.extensions.viewBinding

//Implement local searching, filtering and bookmarking
//Build your user interface using Views and XML (you can use Jetpack Compose for some of UI but do prefer native Views and XML)
//Stay as close as possible to Google recommended architecture (https://developer.android.com/topic/architecture)
//Networking is not required (apart from images)
//Follow the design as closely as possible

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContentView(binding.root)
    }
}