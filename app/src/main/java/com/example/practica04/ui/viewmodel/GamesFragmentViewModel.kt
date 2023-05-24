package com.example.practica04.ui.viewmodel

import android.content.Context
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.practica04.R
import com.example.practica04.data.mock.GamesBoMockList
import com.example.practica04.model.GameBo

class GamesFragmentViewModel(private val repository: GamesBoMockList) : ViewModel() {
    val gamesList = MutableLiveData<List<GameBo>>()
    var sortIdSelected = false
    var sortAlphabetSelected = false

    fun getGames() {
        gamesList.postValue(repository.gameList)
    }

    fun sortGamesId() {
        val sortedListId = gamesList.value
        gamesList.postValue(sortedListId?.sortedBy { it.id })
        sortIdSelected = true
        sortAlphabetSelected = false
    }

    fun sortGamesName() {
        val sortedListName = gamesList.value
        gamesList.postValue(sortedListName?.sortedBy { it.name })
        sortAlphabetSelected = true
        sortIdSelected = false
    }

    fun selectedOrderBtn(iconId: ImageButton, iconAlphabet: ImageButton, context: Context) {
        if (sortIdSelected) {
            iconId.background =
                ContextCompat.getDrawable(context, R.drawable.circular_order_icon_background)
            iconAlphabet.background = null
        } else {
            iconAlphabet.background =
                ContextCompat.getDrawable(context, R.drawable.circular_order_icon_background)
            iconId.background = null
        }
    }

    fun filterGames() {
        val filteredList = gamesList.value
        gamesList.postValue(filteredList?.filter { game ->
            "Nintendo" in game.compatiblePlatform
        })
    }

    fun showDialog(navController: NavController) {
        navController.navigate(R.id.action_gamesFragment_to_gamesFilterDialog)
    }
}