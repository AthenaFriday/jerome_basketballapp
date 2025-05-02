package com.android.basketballapp.data.remote.api

import com.android.basketballapp.data.remote.GameResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GameApi {

    @GET("games")
    suspend fun getGames(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20
    ): GameResponseDto
}
