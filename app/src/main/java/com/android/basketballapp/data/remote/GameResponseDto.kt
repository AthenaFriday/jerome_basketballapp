package com.android.basketballapp.data.remote

import com.android.basketballapp.data.remote.mappers.GameDto
import com.android.basketballapp.data.remote.mappers.MetaDto
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GameResponseDto(
    val data: List<GameDto>,
    val meta: MetaDto
)
