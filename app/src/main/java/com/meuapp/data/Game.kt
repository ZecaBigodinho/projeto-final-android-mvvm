package com.meuapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class Game(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val platform: String,
    val photoUri: String? = null, // Novo campo para a foto
    val isCompleted: Boolean = false
)