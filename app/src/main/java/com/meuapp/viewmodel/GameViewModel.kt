package com.meuapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.meuapp.data.AppDatabase
import com.meuapp.data.Game
import com.meuapp.repository.GameRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GameViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: GameRepository
    val allGames: StateFlow<List<Game>>

    init {
        val gameDao = AppDatabase.getDatabase(application).gameDao()
        repository = GameRepository(gameDao)
        allGames = repository.allGames.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )
    }

    // Salva ou Edita dependendo de ter ID ou não
    fun saveGame(id: Int = 0, title: String, platform: String, photoUri: String?) {
        viewModelScope.launch {
            val game = Game(id = id, title = title, platform = platform, photoUri = photoUri)
            if (id == 0) {
                repository.insert(game)
            } else {
                repository.update(game)
            }
        }
    }

    fun deleteGame(game: Game) {
        viewModelScope.launch {
            repository.delete(game)
        }
    }
}