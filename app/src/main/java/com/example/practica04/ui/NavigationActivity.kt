package com.example.practica04.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practica04.R
import com.example.practica04.data.repository.GamesRepository

class NavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        GamesRepository.initializeGamesRepository(this)
    }
}