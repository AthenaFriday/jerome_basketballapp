package com.android.basketballapp.presentation.games

import app.cash.turbine.test
import com.android.basketballapp.data.Game
import com.android.basketballapp.data.utils.ApiResult
import com.android.domain.usecase.GetGamesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GamesViewModelTest {

    private lateinit var getGamesUseCase: GetGamesUseCase
    private lateinit var viewModel: GamesViewModel

    @Before
    fun setUp() {
        getGamesUseCase = mockk()
    }

    @Test
    fun `initial state should be Loading and then Success`() = runTest {
        // Arrange
        val dummyGames = listOf(Game(id = 1, date = "2025-05-01", homeTeamName = "Lakers", visitorTeamName = "Celtics", homeTeamScore = 110, visitorTeamScore = 102, season = 2025, period = 4, status = "Final", time = "21:00", isPostseason = false))
        coEvery { getGamesUseCase() } returns flowOf(ApiResult.Loading, ApiResult.Success(dummyGames))

        // Act
        viewModel = GamesViewModel(getGamesUseCase)

        // Assert
        viewModel.games.test {
            assertEquals(ApiResult.Loading, awaitItem())
            val result = awaitItem()
            assertTrue(result is ApiResult.Success)
            val games = (result as ApiResult.Success).data
            assertEquals(1, games.size)
            assertEquals("Lakers", games[0].homeTeamName)
            cancel()
        }
    }

    @Test
    fun `should emit Error when use case fails`() = runTest {
        // Arrange
        val errorMsg = "Failed to fetch games"
        coEvery { getGamesUseCase() } returns flowOf(ApiResult.Loading, ApiResult.Error(errorMsg))

        // Act
        viewModel = GamesViewModel(getGamesUseCase)

        // Assert
        viewModel.games.test {
            assertEquals(ApiResult.Loading, awaitItem())
            val error = awaitItem()
            assertTrue(error is ApiResult.Error)
            assertEquals(errorMsg, (error as ApiResult.Error).message)
            cancel()
        }
    }
}
