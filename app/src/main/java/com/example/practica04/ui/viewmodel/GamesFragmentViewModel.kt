package com.example.practica04.ui.viewmodel

import android.content.Context
import android.widget.Button
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.practica04.R
import com.example.practica04.data.repository.GamesRepository
import com.example.practica04.model.CompatiblePlatform
import com.example.practica04.model.GameBo
import kotlinx.coroutines.launch

class GamesFragmentViewModel(private val repository: GamesRepository) : ViewModel() {
    val gamesList = MutableLiveData<List<GameBo>>()
    val sortIdSelected = MutableLiveData<Boolean>()
    val sortAlphabetSelected = MutableLiveData<Boolean>()
    val selectFilter = MutableLiveData<CompatiblePlatform>()

    fun getGames() {
        viewModelScope.launch {
            gamesList.postValue(repository.getGames())
        }
    }

    fun sortGamesId() {
        viewModelScope.launch {
            gamesList.postValue(repository.getGamesSortedById())
        }
        sortIdSelected.value = true
        sortAlphabetSelected.value = false
    }

    fun sortGamesName() {
        viewModelScope.launch {
            gamesList.postValue(repository.getGamesSortedByName())
        }
        sortAlphabetSelected.value = true
        sortIdSelected.value = false
    }

    fun selectedOrderBtn(iconId: ImageButton, iconAlphabet: ImageButton, context: Context) {
        if (sortIdSelected.value == true) {
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
        viewModelScope.launch {
            gamesList.postValue(repository.getGamesFiltered(CompatiblePlatform.NINTENDO))
        }
    }

    fun filterGamesByPlayStation() {
        viewModelScope.launch {
            gamesList.postValue(repository.getGamesFiltered(CompatiblePlatform.PLAYSTATION))
        }
    }

    fun filterGamesByXbox() {
        viewModelScope.launch {
            gamesList.postValue(repository.getGamesFiltered(CompatiblePlatform.XBOX))
        }
    }

    fun restoreGameList() {
        getGames()
    }

    fun selectedFilter(context: Context, button: Button) {
        selectFilter.value = CompatiblePlatform.fromPlatform(button.text.toString())
        button.background = ContextCompat.getDrawable(context, R.drawable.selected_button_backgroun)
    }

    fun showDialog(navController: NavController) {
        navController.navigate(R.id.action_gamesFragment_to_gamesFilterDialog)
    }
}