package com.android.basketballapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.basketballapp.presentation.games.GamesScreen
import com.android.basketballapp.presentation.games.GamesViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    gamesViewModel: GamesViewModel
) {
    NavHost(navController = navController, startDestination = "games") {
        composable("games") {
            GamesScreen(viewModel = gamesViewModel)
        }
    }
}
