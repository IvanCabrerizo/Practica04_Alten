package com.example.practica04.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practica04.data.mock.GamesBoMockList
import com.example.practica04.model.GameBo

class GamesFragmentViewModel(private val repository: GamesBoMockList) : ViewModel() {
    val gamesList = MutableLiveData<List<GameBo>>()

    fun getGames() {
        gamesList.postValue(repository.gameList)
    }

    fun orderGamesId() {
        val sortedListId = gamesList.value
        gamesList.postValue(sortedListId?.sortedBy { it.id })
    }

    fun orderGamesName() {
        val sortedListName = gamesList.value
        gamesList.postValue(sortedListName?.sortedBy { it.name })
    }

    fun filterGames(){
        val filteredList = gamesList.value
        gamesList.postValue(filteredList?.filter { game ->
            "Nintendo" in game.compatiblePlatform
        })
    }
}