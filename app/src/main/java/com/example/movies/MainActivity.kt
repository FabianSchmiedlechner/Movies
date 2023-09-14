package com.example.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

//Implement local searching, filtering and bookmarking
//Build your user interface using Views and XML (you can use Jetpack Compose for some of UI but do prefer native Views and XML)
//Stay as close as possible to Google recommended architecture (https://developer.android.com/topic/architecture)
//Networking is not required (apart from images)
//Follow the design as closely as possible

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}