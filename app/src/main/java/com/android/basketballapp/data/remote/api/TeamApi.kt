
// TeamApi.kt
package com.android.basketballapp.data.remote.api

import com.android.basketballapp.data.remote.mappers.TeamResponseDto
import retrofit2.http.GET

interface TeamApi {
    @GET("teams")
    suspend fun getTeams(): TeamResponseDto
}