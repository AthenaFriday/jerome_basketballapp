package com.android.basketballapp.data.repository

import com.android.basketballapp.data.Game
import com.android.basketballapp.data.utils.ApiResult
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getGames(): Flow<ApiResult<List<Game>>>
}
