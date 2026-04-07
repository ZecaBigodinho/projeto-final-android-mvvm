package com.meuapp.repository

import com.meuapp.data.Game
import com.meuapp.data.GameDao
import kotlinx.coroutines.flow.Flow

class GameRepository(private val gameDao: GameDao) {
    val allGames: Flow<List<Game>> = gameDao.getAllGames()

    suspend fun insert(game: Game) {
        gameDao.insertGame(game)
    }

    suspend fun update(game: Game) {
        gameDao.updateGame(game)
    }

    suspend fun delete(game: Game) {
        gameDao.deleteGame(game)
    }
}