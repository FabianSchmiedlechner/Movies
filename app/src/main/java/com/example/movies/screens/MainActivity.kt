package com.example.movies.screens

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updateMargins
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.extensions.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            binding.root.setOnApplyWindowInsetsListener { _, _ ->
                applyKeyboardInsets(binding.root)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun applyKeyboardInsets(root: View): WindowInsets {

        val insets = root.rootWindowInsets.getInsets(WindowInsets.Type.ime())

        root.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            updateMargins(insets.left, insets.top, insets.right, insets.bottom)
        }
        return WindowInsets.Builder()
            .setInsets(WindowInsets.Type.ime(), insets)
            .build()
    }
}