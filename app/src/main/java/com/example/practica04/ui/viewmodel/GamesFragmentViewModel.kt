package com.example.practica04.ui.viewmodel

import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practica04.data.repository.GamesRepository
import com.example.practica04.model.CompatiblePlatform
import com.example.practica04.model.GameBo
import kotlinx.coroutines.launch

class GamesFragmentViewModel() : ViewModel() {

    private val repository: GamesRepository by lazy { GamesRepository }
    private val gamesList = MutableLiveData<List<GameBo>>()
    val selectFilter: MutableLiveData<CompatiblePlatform> =
        MutableLiveData<CompatiblePlatform>().apply {
            value = CompatiblePlatform.ALL
        }
    val previousSelectedFilter: MutableLiveData<Button> = MutableLiveData<Button>().apply {
        value = null
    }
    val sortSelected: MutableLiveData<SortType> = MutableLiveData<SortType>().apply {
        value = SortType.ID
    }
    val selectFilterButton = MutableLiveData<Button>()
    private val orderDialogStart: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply {
        value = false
    }
    private val itemRecyclerSelected = MutableLiveData<GameBo>()
    private val deleteGame = MutableLiveData<GameBo?>(null)
    private val addGameStart = MutableLiveData<Boolean>(false)



    enum class SortType(name: String) {
        ID("ID"),
        NAME("NAME"),
    }

    fun getGameList(): LiveData<List<GameBo>> {
        return gamesList
    }

    fun getAddGameStart(): LiveData<Boolean> {
        return addGameStart
    }

    fun getItemRecyclerSelected(): LiveData<GameBo> {
        return itemRecyclerSelected
    }

    fun getOrderDialogStart(): LiveData<Boolean> {
        return orderDialogStart
    }

    fun updateItemRecyclerSelected(game: GameBo) {
        itemRecyclerSelected.postValue(game)
    }

    fun getGames() {
        viewModelScope.launch {
            gamesList.postValue(repository.getGames())
        }
    }

    fun sortGames(sort: SortType) {
        viewModelScope.launch {
            when (sort) {
                SortType.ID -> {
                    if (selectFilter.value == null) {
                        gamesList.postValue(repository.getGamesSorted(SortType.ID))
                    } else {
                        filterGames(selectFilter.value ?: CompatiblePlatform.ALL)
                    }
                    sortSelected.value = SortType.ID
                }

                SortType.NAME -> {
                    if (selectFilter.value == null) {
                        gamesList.postValue(repository.getGamesSorted(SortType.NAME))
                    } else {
                        filterGames(selectFilter.value ?: CompatiblePlatform.ALL)
                    }
                    sortSelected.value = SortType.NAME
                }
            }
        }
    }

    fun filterGames(filter: CompatiblePlatform) {
        viewModelScope.launch {
            gamesList.postValue(
                repository.getGamesFiltered(
                    filter,
                    sortSelected.value ?: SortType.ID
                )
            )
        }
    }

    fun selectedFilter(button: Button) {
        selectFilter.value = CompatiblePlatform.fromPlatform(button.text.toString())
        previousSelectedFilter.value = selectFilterButton.value
        selectFilterButton.value = button
    }

    fun showOrderDialog() {
        orderDialogStart.postValue(true)
    }

    fun deleteGame() {
        viewModelScope.launch {
            val deletedGame = itemRecyclerSelected.value
            if (deletedGame != null) {
                deleteGame.postValue(deletedGame)
                repository.deleteGame(deletedGame)
                gamesList.postValue(
                    repository.getGamesFiltered(
                        selectFilter.value ?: CompatiblePlatform.ALL,
                        sortSelected.value ?: SortType.ID
                    )
                )
            }
        }
    }

    fun addGame() {
        deleteGame.postValue(null)
    }

    fun getDeleteGame(): LiveData<GameBo?> = deleteGame

    fun addGame(game: GameBo) {
        viewModelScope.launch {
            repository.addGame(game)
            gamesList.postValue(
                repository.getGamesFiltered(
                    selectFilter.value ?: CompatiblePlatform.ALL,
                    sortSelected.value ?: SortType.ID
                )
            )
        }
    }

    fun addGameFragmentInit() {
        viewModelScope.launch {
            addGameStart.postValue(true)
        }
    }

    fun resetNavigate() {
        addGameStart.value = false
    }
}