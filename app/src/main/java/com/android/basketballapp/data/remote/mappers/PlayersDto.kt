// PlayersDto.kt
package com.android.basketballapp.data.remote.mappers

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlayerDto(
    val id: Int,
    val first_name: String,
    val last_name: String,
    val position: String?,
    val height_feet: Int?,
    val height_inches: Int?,
    val weight_pounds: Int?,
    val team: TeamDto
)
