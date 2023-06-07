package com.example.practica04.data.repository

import android.content.Context
import com.example.practica04.data.mock.GamesBoMockProvider
import com.example.practica04.data.preference.UserPreferencesProvider
import com.example.practica04.model.CompatiblePlatform
import com.example.practica04.model.GameBo
import com.example.practica04.ui.viewmodel.GamesFragmentViewModel
import kotlinx.coroutines.flow.Flow

object GamesRepository {

    private val gameBoMock = GamesBoMockProvider
    private lateinit var dataStore: UserPreferencesProvider

    fun initializeGamesRepository(context: Context) {
        dataStore = UserPreferencesProvider(context)
    }

    suspend fun getGames(): List<GameBo> {
        return gameBoMock.gameList.sortedBy { it.id }
    }

    suspend fun getGamesFiltered(
        platform: CompatiblePlatform, sort: GamesFragmentViewModel.SortType
    ): List<GameBo> {
        return if (platform == CompatiblePlatform.ALL) {
            sortList(sort, gameBoMock.gameList)
        } else {
            val filteredList = gameBoMock.gameList.filter { game ->
                platform in game.compatiblePlatform
            }
            sortList(sort, filteredList)
        }
    }

    suspend fun getGamesSorted(sort: GamesFragmentViewModel.SortType): List<GameBo> {
        return sortList(sort, gameBoMock.gameList)
    }

    suspend fun deleteGame(game: GameBo): List<GameBo> {
        gameBoMock.gameList = gameBoMock.gameList.toMutableList().apply {
            remove(game)
        }
        return gameBoMock.gameList
    }

    suspend fun addGame(game: GameBo): List<GameBo> {
        gameBoMock.gameList = gameBoMock.gameList.toMutableList().apply {
            add(game)
        }
        return gameBoMock.gameList
    }

    suspend fun getGamesByStudio(studio: String): List<GameBo> {
        val filteredList = gameBoMock.gameList.filter { game ->
            studio in game.studio
        }
        return sortList(GamesFragmentViewModel.SortType.ID, filteredList)
    }

    private fun sortList(sort: GamesFragmentViewModel.SortType, list: List<GameBo>): List<GameBo> {
        return when (sort) {
            GamesFragmentViewModel.SortType.ID -> list.sortedBy { it.id }
            GamesFragmentViewModel.SortType.NAME -> list.sortedBy { it.name }
        }
    }

    private fun getFilterDataStore(): Flow<String> {
        return dataStore.getFilterSelected()
    }

    private fun getSortDataStore(): Flow<String> {
        return dataStore.getSortSelected()
    }

    suspend fun updateFilterDataStore(filter: String) {
        dataStore.updateFilterSelected(filter)
    }

    suspend fun updateSortDataStore(sort: String) {
        dataStore.updateSortSelected(sort)
    }
}