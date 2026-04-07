package com.meuapp.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.meuapp.viewmodel.GameViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGameScreen(
    navController: NavController, 
    viewModel: GameViewModel,
    gameId: Int = 0,
    initialTitle: String = "",
    initialPlatform: String = "",
    initialPhoto: String? = null
) {
    var title by remember { mutableStateOf(initialTitle) }
    var platform by remember { mutableStateOf(initialPlatform) }
    var photoUri by remember { mutableStateOf<Uri?>(initialPhoto?.let { Uri.parse(it) }) }

    // Lançador da Galeria de Fotos
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> photoUri = uri }
    )

    Scaffold(
        containerColor = DarkBackground,
        topBar = { 
            TopAppBar(
                title = { Text(if (gameId == 0) "INITIALIZE_NEW_GAME" else "OVERRIDE_GAME_DATA", color = NeonPurple, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBackground)
            ) 
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            
            // Preview da Imagem
            if (photoUri != null) {
                AsyncImage(
                    model = photoUri,
                    contentDescription = "Capa do Jogo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().height(200.dp).background(CardBackground, RoundedCornerShape(8.dp))
                )
            }

            Button(
                onClick = { photoPickerLauncher.launch(
                    androidx.activity.result.PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )},
                colors = ButtonDefaults.buttonColors(containerColor = CardBackground),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (photoUri == null) "Anexar Imagem [Opcional]" else "Trocar Imagem", color = NeonGreen)
            }

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Título", color = Color.Gray) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = NeonGreen,
                    unfocusedBorderColor = Color.DarkGray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = platform,
                onValueChange = { platform = it },
                label = { Text("Plataforma (ex: PS2, PC)", color = Color.Gray) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = NeonGreen,
                    unfocusedBorderColor = Color.DarkGray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (title.isNotBlank() && platform.isNotBlank()) {
                        viewModel.saveGame(gameId, title, platform, photoUri?.toString())
                        navController.popBackStack() 
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = NeonPurple),
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text("EXECUTAR_SAVE()", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}