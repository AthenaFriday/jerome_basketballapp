package com.android.basketballapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.android.basketballapp.presentation.games.GamesViewModel
import com.android.basketballapp.presentation.navigation.AppNavGraph
import com.android.basketballapp.presentation.players.PlayersViewModel
import com.android.basketballapp.teams.TeamsViewModel
import com.android.basketballapp.ui.theme.BasketballAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val gamesViewModel: GamesViewModel by viewModels()
    private val playersViewModel: PlayersViewModel by viewModels()
    private val teamsViewModel: TeamsViewModel by viewModels() // ✅ ADD THIS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasketballAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    AppNavGraph(
                        navController = navController,
                        gamesViewModel = gamesViewModel,
                        playersViewModel = playersViewModel,
                        teamsViewModel = teamsViewModel // ✅ PASS TO NAVGRAPH
                    )
                }
            }
        }
    }
}
