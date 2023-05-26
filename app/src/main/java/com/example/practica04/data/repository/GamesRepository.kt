package com.example.practica04.data.repository

import com.example.practica04.data.mock.GamesBoMockList
import com.example.practica04.model.CompatiblePlatform
import com.example.practica04.model.GameBo

class GamesRepository(private val gameBoMock: GamesBoMockList) {

    suspend fun getGames(): List<GameBo> {
        return gameBoMock.gameList
    }

    suspend fun getGamesFiltered(platform: CompatiblePlatform): List<GameBo> {
        return gameBoMock.gameList.filter { game ->
            platform in game.compatiblePlatform
        }
    }

    suspend fun getGamesSortedById(): List<GameBo> {
        return gameBoMock.gameList.sortedBy { it.id }
    }

    suspend fun getGamesSortedByName(): List<GameBo> {
        return gameBoMock.gameList.sortedBy { it.name }
    }
}

/*
class GamesRepository(private val gameDatabase: GamesDataBase) {

    suspend fun getGames(sort: Boolean): List<GameBo> {
        return gameDataBase.gamesDao().getAllSorted(sort)
    }
}*/
