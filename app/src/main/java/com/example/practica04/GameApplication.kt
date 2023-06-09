package com.example.practica04

import android.app.Application
import com.example.practica04.data.repository.GamesRepository

class GameApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        GamesRepository.initializeGamesRepository(this)
    }
}