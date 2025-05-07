package com.android.domain.usecase

import com.android.basketballapp.data.Game
import com.android.basketballapp.data.repository.GameRepository
import com.android.basketballapp.data.utils.ApiResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetGamesUseCaseTest {

    private lateinit var repository: GameRepository
    private lateinit var useCase: GetGamesUseCase

    @Before
    fun setUp() {
        repository = mockk()
        useCase = GetGamesUseCase(repository)
    }

    @Test
    fun `invoke should return flow of Success with games list`() = runTest {
        // Arrange
        val dummyGames = listOf(Game(id = 1, date = "2025-05-01", homeTeamName = "Lakers", visitorTeamName = "Celtics", homeTeamScore = 102, visitorTeamScore = 99, season = 2025, period = 4, status = "Final", time = "21:00", isPostseason = false))
        coEvery { repository.getGames() } returns flowOf(ApiResult.Success(dummyGames))

        // Act
        val result = useCase().toList()

        // Assert
        assertEquals(1, result.size)
        assertTrue(result[0] is ApiResult.Success)
        val games = (result[0] as ApiResult.Success).data
        assertEquals(1, games.size)
        assertEquals("Lakers", games[0].homeTeamName)
    }

    @Test
    fun `invoke should return flow of Error when repository emits error`() = runTest {
        // Arrange
        val errorMessage = "Something went wrong"
        coEvery { repository.getGames() } returns flowOf(ApiResult.Error(errorMessage))

        // Act
        val result = useCase().toList()

        // Assert
        assertEquals(1, result.size)
        assertTrue(result[0] is ApiResult.Error)
        val error = result[0] as ApiResult.Error
        assertEquals(errorMessage, error.message)
    }
}
