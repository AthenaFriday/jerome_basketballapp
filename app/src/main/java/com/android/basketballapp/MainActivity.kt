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
import com.android.basketballapp.ui.theme.BasketballAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.text.Typography.dagger

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val gamesViewModel: GamesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasketballAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    AppNavGraph(navController, gamesViewModel)
                }
            }
        }
    }
}
