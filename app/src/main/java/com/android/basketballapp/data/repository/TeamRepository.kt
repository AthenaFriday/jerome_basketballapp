
// TeamRepository.kt
package com.android.basketballapp.data.repository

import com.android.basketballapp.data.Team
import com.android.basketballapp.data.utils.ApiResult
import kotlinx.coroutines.flow.Flow

interface TeamRepository {
    fun getTeams(): Flow<ApiResult<List<Team>>>
}