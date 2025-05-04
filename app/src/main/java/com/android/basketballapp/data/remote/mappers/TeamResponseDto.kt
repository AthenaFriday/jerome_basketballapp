
// TeamResponseDto.kt
package com.android.basketballapp.data.remote.mappers

import com.android.basketballapp.data.remote.mappers.TeamDto
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TeamResponseDto(
    val data: List<TeamDto>
)