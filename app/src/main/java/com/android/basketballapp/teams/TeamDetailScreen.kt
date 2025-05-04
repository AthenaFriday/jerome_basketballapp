// TeamDetailScreen.kt with scroll support and TopAppBar
package com.android.basketballapp.teams

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.android.basketballapp.data.Team

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamDetailScreen(team: Team, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(team.fullName) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = "City: ${team.city}")
            Text(text = "Conference: ${team.conference}")
            Text(text = "Division: ${team.division}")
            Text(text = "Abbreviation: ${team.abbreviation}")
        }
    }
}