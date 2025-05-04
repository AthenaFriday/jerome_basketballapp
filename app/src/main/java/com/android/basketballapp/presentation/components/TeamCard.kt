// TeamCard.kt
package com.android.basketballapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.basketballapp.data.Team

@Composable
fun TeamCard(
    team: Team,
    onClick: (Team) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(team) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = team.fullName,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "City: ${team.city}")
            Text(text = "Conference: ${team.conference}")
            Text(text = "Division: ${team.division}")
            Text(text = "Abbreviation: ${team.abbreviation}")
        }
    }
}
