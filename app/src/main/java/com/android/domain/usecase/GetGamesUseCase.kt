package com.android.domain.usecase

import com.android.basketballapp.data.Game
import com.android.basketballapp.data.repository.GameRepository
import com.android.basketballapp.data.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
    private val repository: GameRepository
) {
    operator fun invoke(): Flow<ApiResult<List<Game>>> =
        repository.getGames()
}
