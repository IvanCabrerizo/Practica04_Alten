package com.example.practica04.data.repository

import com.example.practica04.data.mock.GamesBoMockProvider
import com.example.practica04.model.CompatiblePlatform
import com.example.practica04.model.GameBo
import com.example.practica04.ui.viewmodel.GamesFragmentViewModel

class GamesRepository(private val gameBoMock: GamesBoMockProvider) {

    suspend fun getGames(): List<GameBo> {
        return gameBoMock.gameList.sortedBy { it.id }
    }

    suspend fun getGamesFiltered(
        platform: CompatiblePlatform,
        sort: GamesFragmentViewModel.SortType
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

    private fun sortList(sort: GamesFragmentViewModel.SortType, list: List<GameBo>): List<GameBo> {
        return when (sort) {
            GamesFragmentViewModel.SortType.ID -> list.sortedBy { it.id }
            GamesFragmentViewModel.SortType.NAME -> list.sortedBy { it.name }
        }
    }
}

/*
class GamesRepository(private val gameDatabase: GamesDataBase) {

    suspend fun getGames(sort: Boolean): List<GameBo> {
        return gameDataBase.gamesDao().getAllSorted(sort)
    }
}*/
