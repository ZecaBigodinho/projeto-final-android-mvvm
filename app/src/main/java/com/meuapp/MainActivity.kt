package com.meuapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.meuapp.ui.AddGameScreen
import com.meuapp.ui.GameListScreen
import com.meuapp.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Usando o tema nativo do Material Design 3
            MaterialTheme {
                val navController = rememberNavController()
                // Instancia o ViewModel escopado na Activity
                val gameViewModel: GameViewModel = viewModel()

                NavHost(navController = navController, startDestination = "game_list") {
                    composable("game_list") {
                        GameListScreen(navController, gameViewModel)
                    }
                    // Rota de adicionar e editar combinadas com argumentos
                    composable(
                        route = "add_game/{gameId}?title={title}&platform={platform}&photo={photo}",
                        arguments = listOf(
                            androidx.navigation.navArgument("gameId") { type = androidx.navigation.NavType.IntType },
                            androidx.navigation.navArgument("title") { defaultValue = "" },
                            androidx.navigation.navArgument("platform") { defaultValue = "" },
                            androidx.navigation.navArgument("photo") { nullable = true; defaultValue = null }
                        )
                    ) { backStackEntry ->
                        val gameId = backStackEntry.arguments?.getInt("gameId") ?: 0
                        val title = backStackEntry.arguments?.getString("title") ?: ""
                        val platform = backStackEntry.arguments?.getString("platform") ?: ""
                        val photo = backStackEntry.arguments?.getString("photo")
                        AddGameScreen(navController, gameViewModel, gameId, title, platform, photo)
                    }
                }
            }
        }
    }
}