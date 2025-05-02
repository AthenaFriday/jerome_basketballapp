package com.android.basketballapp.data.repository

import com.android.basketballapp.data.Game
import com.android.basketballapp.data.remote.api.GameApi
import com.android.basketballapp.data.remote.mappers.toDomain
import com.android.basketballapp.data.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val api: GameApi
) : GameRepository {

    override fun getGames(): Flow<ApiResult<List<Game>>> = flow {
        emit(ApiResult.Loading)

        try {
            val response = api.getGames()
            val games = response.data.map { it.toDomain() } // ✅ Make sure response is of type GameResponseDto
            emit(ApiResult.Success(games))
        } catch (e: Exception) {
            val message = e.localizedMessage ?: "Something went wrong while fetching games."
            emit(ApiResult.Error(message))
        }
    }
}
