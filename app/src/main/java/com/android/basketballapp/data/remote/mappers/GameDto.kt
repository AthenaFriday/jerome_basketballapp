package com.android.basketballapp.data.remote.mappers

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GameDto(
    val id: Int,
    val date: String,
    @Json(name = "home_team") val homeTeam: TeamDto,
    @Json(name = "home_team_score") val homeTeamScore: Int,
    @Json(name = "visitor_team") val visitorTeam: TeamDto,
    @Json(name = "visitor_team_score") val visitorTeamScore: Int,
    val season: Int,
    val period: Int,
    val status: String,
    val time: String?, // ✅ Make nullable
    val postseason: Boolean
)
