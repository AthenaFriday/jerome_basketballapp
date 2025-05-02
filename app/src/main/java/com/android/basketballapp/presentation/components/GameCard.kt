package com.android.basketballapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.basketballapp.data.Game

@Composable
fun GameCard(game: Game) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "${game.homeTeamName} vs ${game.visitorTeamName}")
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Date: ${game.date}")
        }
    }
}
