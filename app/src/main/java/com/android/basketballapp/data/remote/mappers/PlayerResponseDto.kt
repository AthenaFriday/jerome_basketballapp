package com.android.basketballapp.data.remote.mappers

// PlayerResponseDto.kt
import com.android.basketballapp.data.remote.mappers.PlayerDto
import com.android.basketballapp.data.remote.mappers.MetaDto
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlayerResponseDto(
    val data: List<PlayerDto>,
    val meta: MetaDto
)