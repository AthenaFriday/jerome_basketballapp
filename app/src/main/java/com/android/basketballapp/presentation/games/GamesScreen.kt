package com.android.basketballapp.presentation.games

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // ✅ Required to avoid "expected Int" error
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.basketballapp.data.utils.ApiResult
import com.android.basketballapp.presentation.components.GameCard

@Composable
fun GamesScreen(viewModel: GamesViewModel) {
    val gameState by viewModel.games.collectAsState()

    when (val state = gameState) {
        is ApiResult.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is ApiResult.Success -> {
            val games = state.data
            LazyColumn(modifier = Modifier.padding(16.dp)) {
                items(games) { game ->
                    GameCard(game = game)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        is ApiResult.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Error: ${state.message}",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
