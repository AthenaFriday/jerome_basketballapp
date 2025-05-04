
// PlayersScreen.kt with TopAppBar
package com.android.basketballapp.presentation.players

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.android.basketballapp.data.utils.ApiResult
import com.android.basketballapp.presentation.components.PlayerCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayersScreen(viewModel: PlayersViewModel, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Players") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        val state by viewModel.players.collectAsState()

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {
            when (val result = state) {
                is ApiResult.Loading -> Box(Modifier.fillMaxSize(), Alignment.Center) {
                    CircularProgressIndicator()
                }
                is ApiResult.Success -> LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(result.data) { player ->
                        PlayerCard(player = player)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
                is ApiResult.Error -> Box(Modifier.fillMaxSize(), Alignment.Center) {
                    Text("Error: ${result.message}", color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}