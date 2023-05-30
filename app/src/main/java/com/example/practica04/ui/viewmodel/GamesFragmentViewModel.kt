package com.example.practica04.ui.viewmodel

import android.content.Context
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.practica04.R
import com.example.practica04.data.mock.GamesBoMockProvider
import com.example.practica04.data.repository.GamesRepository
import com.example.practica04.model.CompatiblePlatform
import com.example.practica04.model.GameBo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GamesFragmentViewModel() : ViewModel() {

    private val repository: GamesRepository by lazy { GamesRepository(GamesBoMockProvider) }
    val gamesList = MutableLiveData<List<GameBo>>()
    val selectFilter = MutableLiveData<CompatiblePlatform?>()
    val previousSelectedFilter: MutableLiveData<Button> = MutableLiveData<Button>().apply {
        value = null
    }
    val sortSelected: MutableLiveData<SortType> = MutableLiveData<SortType>().apply {
        value = SortType.ID
    }
    val selectFilterButton = MutableLiveData<Button>()

    enum class SortType(name: String) {
        ID("ID"),
        NAME("NAME"),
    }

    fun getGames() {
        viewModelScope.launch {
            gamesList.postValue(repository.getGames())
        }
    }

    fun sortGames(sort: SortType, recycler: RecyclerView) {
        viewModelScope.launch {
            when (sort) {
                SortType.ID -> {
                    if (selectFilter.value == null) {
                        gamesList.postValue(repository.getGamesSorted(SortType.ID))
                    } else {
                        filterGames(selectFilter.value ?: return@launch)
                    }
                    sortSelected.value = SortType.ID
                }

                SortType.NAME -> {
                    if (selectFilter.value == null) {
                        gamesList.postValue(repository.getGamesSorted(SortType.NAME))
                    } else {
                        filterGames(selectFilter.value ?: return@launch)
                    }
                    sortSelected.value = SortType.NAME
                }
            }
            delay(100)
            recycler.scrollToPosition(0)
        }
    }

    fun filterGames(filter: CompatiblePlatform) {
        viewModelScope.launch {
            gamesList.postValue(
                repository.getGamesFiltered(
                    filter,
                    sortSelected.value ?: return@launch
                )
            )
        }
    }

    fun restoreGameList() {
        getGames()
    }

    fun selectedFilter(button: Button) {
        selectFilter.value = CompatiblePlatform.fromPlatform(button.text.toString())
        previousSelectedFilter.value = selectFilterButton.value
        selectFilterButton.value = button
    }

    fun showDialog(navController: NavController) {
        navController.navigate(R.id.action_gamesFragment_to_gamesFilterDialog)
    }
}