// NavGraph.kt with back navigation support
package com.android.basketballapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.basketballapp.data.Team
import com.android.basketballapp.presentation.games.GamesScreen
import com.android.basketballapp.presentation.games.GamesViewModel
import com.android.basketballapp.presentation.players.PlayersScreen
import com.android.basketballapp.presentation.players.PlayersViewModel
import com.android.basketballapp.teams.TeamDetailScreen
import com.android.basketballapp.teams.TeamsScreen
import com.android.basketballapp.teams.TeamsViewModel
import java.net.URLDecoder

@Composable
fun AppNavGraph(
    navController: NavHostController,
    gamesViewModel: GamesViewModel,
    playersViewModel: PlayersViewModel,
    teamsViewModel: TeamsViewModel
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }
        composable("games") {
            GamesScreen(viewModel = gamesViewModel, navController = navController)
        }
        composable("players") {
            PlayersScreen(viewModel = playersViewModel, navController = navController)
        }
        composable("teams") {
            TeamsScreen(viewModel = teamsViewModel, navController = navController)
        }
        composable("team_detail/{teamName}") { backStackEntry ->
            val teamName = backStackEntry.arguments?.getString("teamName")?.let { URLDecoder.decode(it, "UTF-8") } ?: "Unknown Team"
            TeamDetailScreen(team = Team(
                id = 0,
                name = teamName,
                fullName = teamName,
                city = "",
                conference = "",
                division = "",
                abbreviation = ""
            ), navController = navController)
        }
    }
}
