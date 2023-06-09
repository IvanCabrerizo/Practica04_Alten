package com.example.practica04.data.repository

import android.content.Context
import android.util.Log
import com.example.practica04.data.mock.GamesBoMockProvider
import com.example.practica04.data.preference.UserPreferencesProvider
import com.example.practica04.model.CompatiblePlatform
import com.example.practica04.model.GameBo
import com.example.practica04.ui.viewmodel.GamesFragmentViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

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
            updateFilterDataStore(CompatiblePlatform.ALL.platform)
            sortList(sort, gameBoMock.gameList)
        } else {
            Log.i("MANOLO", "Valor repository filter: ${platform.platform}")
            updateFilterDataStore(platform.platform)
            val filteredList = gameBoMock.gameList.filter { game ->
                platform in game.compatiblePlatform
            }
            sortList(sort, filteredList)
        }
    }

    suspend fun getGamesSorted(sort: GamesFragmentViewModel.SortType): List<GameBo> {
        Log.i("MANOLO", "Valor repository filter: ${sort.name}")
        updateSortDataStore(sort.name)
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

    fun getSort(): Flow<GamesFragmentViewModel.SortType> {
        return dataStore.getSortSelected().map { sort ->
            when (sort) {
                GamesFragmentViewModel.SortType.ID.name -> GamesFragmentViewModel.SortType.ID
                GamesFragmentViewModel.SortType.NAME.name -> GamesFragmentViewModel.SortType.NAME
                else -> GamesFragmentViewModel.SortType.ID
            }
        }
    }

    fun getFilter(): Flow<CompatiblePlatform> {
        return dataStore.getFilterSelected().map { filter ->
            when (filter) {
                CompatiblePlatform.PLAYSTATION.platform -> CompatiblePlatform.PLAYSTATION
                CompatiblePlatform.XBOX.platform -> CompatiblePlatform.XBOX
                CompatiblePlatform.NINTENDO.platform -> CompatiblePlatform.NINTENDO
                else -> CompatiblePlatform.ALL
            }
        }
    }

    private suspend fun updateFilterDataStore(filter: String) {
        dataStore.updateFilterSelected(filter)
    }

    private suspend fun updateSortDataStore(sort: String) {
        dataStore.updateSortSelected(sort)
    }
}