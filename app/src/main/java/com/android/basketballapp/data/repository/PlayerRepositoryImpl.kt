package com.android.basketballapp.data.repository

import com.android.basketballapp.data.Player
import com.android.basketballapp.data.remote.api.PlayerApi
import com.android.basketballapp.data.remote.mappers.toDomain
import com.android.basketballapp.data.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlayerRepositoryImpl @Inject constructor(
    private val api: PlayerApi
) : PlayerRepository {
    override fun getPlayers(): Flow<ApiResult<List<Player>>> = flow {
        emit(ApiResult.Loading)
        try {
            val response = api.getPlayers()
            val players = response.data.map { it.toDomain() } // ✅ should now be resolved
            emit(ApiResult.Success(players))
        } catch (e: Exception) {
            emit(ApiResult.Error(e.localizedMessage ?: "Unknown Error"))
        }
    }
}
