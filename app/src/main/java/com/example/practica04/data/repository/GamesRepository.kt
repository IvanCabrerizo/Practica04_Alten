package com.example.practica04.data.repository

import com.example.practica04.data.mock.GamesBoMockList
import com.example.practica04.model.CompatiblePlatform
import com.example.practica04.model.GameBo
import com.example.practica04.ui.viewmodel.GamesFragmentViewModel

class GamesRepository(private val gameBoMock: GamesBoMockList) {

    suspend fun getGames(): List<GameBo> {
        return gameBoMock.gameList.sortedBy { it.name }
    }

    suspend fun getGamesFiltered(
        platform: CompatiblePlatform,
        sort: GamesFragmentViewModel.SortType?
    ): List<GameBo> {
        val filteredList = gameBoMock.gameList.filter { game ->
            platform in game.compatiblePlatform
        }

        return when (sort) {
            GamesFragmentViewModel.SortType.ID -> filteredList.sortedBy { it.id }
            GamesFragmentViewModel.SortType.NAME -> filteredList.sortedBy { it.name }
            else -> filteredList
        }
    }

    suspend fun getGamesSorted(sort: GamesFragmentViewModel.SortType?): List<GameBo> {
        return when (sort) {
            GamesFragmentViewModel.SortType.ID -> gameBoMock.gameList.sortedBy { it.id }
            GamesFragmentViewModel.SortType.NAME -> gameBoMock.gameList.sortedBy { it.name }
            else -> gameBoMock.gameList.sortedBy { it.name }
        }
    }
}

/*
class GamesRepository(private val gameDatabase: GamesDataBase) {

    suspend fun getGames(sort: Boolean): List<GameBo> {
        return gameDataBase.gamesDao().getAllSorted(sort)
    }
}*/
