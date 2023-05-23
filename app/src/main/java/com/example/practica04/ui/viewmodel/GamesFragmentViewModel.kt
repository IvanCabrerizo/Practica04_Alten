package com.example.practica04.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practica04.data.mock.GamesBoMockList
import com.example.practica04.model.GameBo

class GamesFragmentViewModel(private val repository: GamesBoMockList) : ViewModel() {
    val gamesList = MutableLiveData<MutableList<GameBo>>()

    fun getGames() {
        gamesList.postValue(repository.gameList)
    }
}