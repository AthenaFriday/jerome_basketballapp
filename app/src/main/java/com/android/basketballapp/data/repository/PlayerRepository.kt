// PlayerRepository.kt
package com.android.basketballapp.data.repository

import com.android.basketballapp.data.Player
import com.android.basketballapp.data.utils.ApiResult
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {
    fun getPlayers(): Flow<ApiResult<List<Player>>>
}