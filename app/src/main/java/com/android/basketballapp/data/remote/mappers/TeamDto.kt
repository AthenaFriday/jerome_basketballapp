package com.android.basketballapp.data.remote.mappers

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TeamDto(
    val id: Int,
    val abbreviation: String,
    val city: String,
    val conference: String,
    val division: String,
    @Json(name = "full_name") val fullName: String,
    val name: String
)
