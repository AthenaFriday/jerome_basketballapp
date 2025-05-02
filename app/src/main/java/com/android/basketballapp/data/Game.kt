package com.android.basketballapp.data


data class Game(
    val id: Int,
    val date: String,
    val homeTeamName: String,
    val visitorTeamName: String,
    val homeTeamScore: Int,
    val visitorTeamScore: Int,
    val season: Int,
    val period: Int,
    val status: String,
    val time: String,
    val isPostseason: Boolean
)