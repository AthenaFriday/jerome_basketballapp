package com.android.basketballapp.data.remote.mappers

import com.android.basketballapp.data.Game

fun GameDto.toDomain(): Game {
    return Game(
        id = id ?: -1,
        date = date ?: "Unknown Date",
        homeTeamName = homeTeam?.fullName ?: "Unknown Home Team",
        visitorTeamName = visitorTeam?.fullName ?: "Unknown Visitor Team",
        homeTeamScore = homeTeamScore ?: 0,
        visitorTeamScore = visitorTeamScore ?: 0,
        season = season ?: 0,
        period = period ?: 0,
        status = status ?: "Unknown Status",
        time = time ?: "N/A",
        isPostseason = postseason ?: false
    )
}
