
// GetTeamsUseCase.kt
package com.android.domain.usecase

import com.android.basketballapp.data.Team
import com.android.basketballapp.data.repository.TeamRepository
import com.android.basketballapp.data.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTeamsUseCase @Inject constructor(
    private val repository: TeamRepository
) {
    operator fun invoke(): Flow<ApiResult<List<Team>>> =
        repository.getTeams()
}