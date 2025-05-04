// PlayerCard.kt
package com.android.basketballapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.basketballapp.data.Player

@Composable
fun PlayerCard(player: Player) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = player.fullName)
            Text(text = "Team: ${player.teamName}")
            Text(text = "Position: ${player.position}")
            Text(text = "Height: ${player.height}, Weight: ${player.weight}")
        }
    }
}