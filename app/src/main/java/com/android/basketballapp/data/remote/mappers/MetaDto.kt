package com.android.basketballapp.data.remote.mappers

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MetaDto(
    @Json(name = "total_pages") val totalPages: Int? = null,
    @Json(name = "current_page") val currentPage: Int? = null,
    @Json(name = "next_page") val nextPage: Int? = null,
    @Json(name = "per_page") val perPage: Int? = null,
    @Json(name = "total_count") val totalCount: Int? = null
)
