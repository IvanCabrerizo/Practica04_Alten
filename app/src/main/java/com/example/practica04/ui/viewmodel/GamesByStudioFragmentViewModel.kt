package com.example.practica04.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practica04.data.mock.GamesBoMockProvider
import com.example.practica04.data.repository.GamesRepository
import com.example.practica04.model.GameBo
import kotlinx.coroutines.launch

class GamesByStudioFragmentViewModel : ViewModel() {
    private val repository: GamesRepository by lazy { GamesRepository(GamesBoMockProvider) }
    private val gamesList = MutableLiveData<List<GameBo>>()

    fun getGameList(): LiveData<List<GameBo>> {
        return gamesList
    }

    fun getGames(studio: String) {
        viewModelScope.launch {
            gamesList.postValue(repository.getGamesByStudio(studio))
        }
    }
}