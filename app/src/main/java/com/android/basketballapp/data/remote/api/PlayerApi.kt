// PlayerApi.kt
package com.android.basketballapp.data.remote.api

import com.android.basketballapp.data.remote.mappers.PlayerResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PlayerApi {
    @GET("players")
    suspend fun getPlayers(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 30
    ): PlayerResponseDto
}