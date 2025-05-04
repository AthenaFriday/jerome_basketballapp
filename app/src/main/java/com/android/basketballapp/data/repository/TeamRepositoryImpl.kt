// TeamRepositoryImpl.kt
package com.android.basketballapp.data.repository

import com.android.basketballapp.data.Team
import com.android.basketballapp.data.remote.api.TeamApi
import com.android.basketballapp.data.remote.mappers.toDomain
import com.android.basketballapp.data.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TeamRepositoryImpl @Inject constructor(
    private val api: TeamApi
) : TeamRepository {
    override fun getTeams(): Flow<ApiResult<List<Team>>> = flow {
        emit(ApiResult.Loading)
        try {
            val response = api.getTeams()
            val teams = response.data.map { it.toDomain() }
            emit(ApiResult.Success(teams))
        } catch (e: Exception) {
            emit(ApiResult.Error(e.localizedMessage ?: "Unknown Error"))
        }
    }
}