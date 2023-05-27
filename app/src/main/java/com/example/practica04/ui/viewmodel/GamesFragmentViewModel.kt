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
    val selectFilter = MutableLiveData<CompatiblePlatform?>()
    val sortSelected: MutableLiveData<SortType> = MutableLiveData<SortType>().apply {
        value = SortType.NAME
    }

    enum class SortType(name: String) {
        ID("ID"),
        NAME("NAME"),
    }

    fun getGames() {
        viewModelScope.launch {
            gamesList.postValue(repository.getGames())
        }
    }

    fun sortGames(sort: String) {
        viewModelScope.launch {
            when (sort) {
                "ID" -> {
                    if (selectFilter.value == null) {
                        gamesList.postValue(repository.getGamesSorted(SortType.ID))
                    } else {
                        filterGames(selectFilter.value)
                    }
                    sortSelected.value = SortType.ID
                }

                "NAME" -> {
                    if (selectFilter.value == null) {
                        gamesList.postValue(repository.getGamesSorted(SortType.NAME))
                    } else {
                        filterGames(selectFilter.value)
                    }
                    sortSelected.value = SortType.NAME
                }
            }
        }
    }

    fun selectedOrderBtn(iconId: ImageButton, iconAlphabet: ImageButton, context: Context) {
        if (sortSelected.value == SortType.ID) {
            iconId.background =
                ContextCompat.getDrawable(context, R.drawable.circular_order_icon_background)
            iconAlphabet.background = null
        } else {
            iconAlphabet.background =
                ContextCompat.getDrawable(context, R.drawable.circular_order_icon_background)
            iconId.background = null
        }
    }

    fun filterGames(filter: CompatiblePlatform?) {
        when (filter) {
            CompatiblePlatform.NINTENDO -> {
                viewModelScope.launch {
                    gamesList.postValue(
                        repository.getGamesFiltered(CompatiblePlatform.NINTENDO, sortSelected.value)
                    )
                }
            }

            CompatiblePlatform.PLAYSTATION -> {
                viewModelScope.launch {
                    gamesList.postValue(
                        repository.getGamesFiltered(
                            CompatiblePlatform.PLAYSTATION,
                            sortSelected.value
                        )
                    )
                }
            }

            CompatiblePlatform.XBOX -> {
                viewModelScope.launch {
                    gamesList.postValue(
                        repository.getGamesFiltered(CompatiblePlatform.XBOX, sortSelected.value)
                    )
                }
            }

            CompatiblePlatform.ALL -> {
                viewModelScope.launch {
                    gamesList.postValue(
                        repository.getGames()
                    )
                }
                selectFilter.value = null
            }

            else -> {
                viewModelScope.launch {
                    gamesList.postValue(
                        repository.getGames()
                    )
                }
            }
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