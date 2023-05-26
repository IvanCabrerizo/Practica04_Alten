package com.example.practica04.ui.viewmodel

import android.content.Context
import android.widget.Button
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.practica04.R
import com.example.practica04.data.mock.GamesBoMockList
import com.example.practica04.model.CompatiblePlatform
import com.example.practica04.model.GameBo

class GamesFragmentViewModel(private val repository: GamesBoMockList) : ViewModel() {
    val gamesList = MutableLiveData<List<GameBo>>()
    var sortIdSelected = false
    var sortAlphabetSelected = false
    val selectFilter = MutableLiveData<CompatiblePlatform>()

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

    fun filterGamesByNintendo() {
        val filteredList = repository.gameList
        gamesList.postValue(filteredList.filter { game ->
            CompatiblePlatform.NINTENDO in game.compatiblePlatform
        })
    }

    fun filterGamesByPlayStation() {
        val filteredList = repository.gameList
        gamesList.postValue(filteredList.filter { game ->
            CompatiblePlatform.PLAYSTATION in game.compatiblePlatform
        })
    }

    fun filterGamesByXbox() {
        val filteredList = repository.gameList
        gamesList.postValue(filteredList.filter { game ->
            CompatiblePlatform.XBOX in game.compatiblePlatform
        })
    }

    fun restoreGameList() {
        getGames()
    }

    fun selectedFilter(context: Context, button: Button) {
        selectFilter.value = when (button.text) {
            CompatiblePlatform.PLAYSTATION.platform -> CompatiblePlatform.PLAYSTATION
            CompatiblePlatform.XBOX.platform -> CompatiblePlatform.XBOX
            CompatiblePlatform.NINTENDO.platform -> CompatiblePlatform.NINTENDO
            else -> CompatiblePlatform.ALL
        }

        button.background = ContextCompat.getDrawable(context, R.drawable.selected_button_backgroun)
    }

    fun showDialog(navController: NavController) {
        navController.navigate(R.id.action_gamesFragment_to_gamesFilterDialog)
    }
}