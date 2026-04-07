package com.meuapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.meuapp.viewmodel.GameViewModel

// Paleta Cyberpunk / Hacker
val DarkBackground = Color(0xFF0D0D12)
val CardBackground = Color(0xFF1A1A24)
val NeonGreen = Color(0xFF00FFCC)
val NeonPurple = Color(0xFFB000FF)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameListScreen(navController: NavController, viewModel: GameViewModel) {
    val games by viewModel.allGames.collectAsState()

    Scaffold(
        containerColor = DarkBackground,
        topBar = {
            TopAppBar(
                title = { Text("MEU_BACKLOG.exe", color = NeonGreen, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBackground)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add_game/0") }, // 0 indica um jogo novo
                containerColor = NeonPurple,
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar Jogo")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            items(games) { game ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = CardBackground),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .border(1.dp, NeonGreen.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Foto do Jogo (Se houver)
                        if (game.photoUri != null) {
                            AsyncImage(
                                model = game.photoUri,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(64.dp)
                                    .background(Color.DarkGray, RoundedCornerShape(8.dp))
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                        }

                        // Informações
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = game.title, color = Color.White, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                            Text(text = "> ${game.platform}", color = NeonGreen.copy(alpha = 0.8f), style = MaterialTheme.typography.bodyMedium)
                        }

                        // Botões de Ação
                        IconButton(onClick = { 
                            // Passa os dados via navegação para edição
                            navController.navigate("add_game/${game.id}?title=${game.title}&platform=${game.platform}&photo=${game.photoUri}")
                        }) {
                            Icon(Icons.Default.Edit, contentDescription = "Editar", tint = NeonGreen)
                        }
                        IconButton(onClick = { viewModel.deleteGame(game) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Deletar", tint = Color.Red)
                        }
                    }
                }
            }
        }
    }
}