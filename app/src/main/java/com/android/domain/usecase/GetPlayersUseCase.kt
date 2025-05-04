// GetPlayersUseCase.kt
package com.android.domain.usecase

import com.android.basketballapp.data.Player
import com.android.basketballapp.data.repository.PlayerRepository
import com.android.basketballapp.data.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlayersUseCase @Inject constructor(
    private val repository: PlayerRepository
) {
    operator fun invoke(): Flow<ApiResult<List<Player>>> =
        repository.getPlayers()
}
