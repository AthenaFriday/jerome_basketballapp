package com

import com.android.basketballapp.data.Player
import com.android.basketballapp.data.repository.PlayerRepository
import com.android.basketballapp.data.utils.ApiResult
import com.android.domain.usecase.GetPlayersUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetPlayersUseCaseTest {

    private lateinit var repository: PlayerRepository
    private lateinit var useCase: GetPlayersUseCase

    @Before
    fun setUp() {
        repository = mockk()
        useCase = GetPlayersUseCase(repository)
    }

    @Test
    fun `invoke should return flow of Success with players list`() = runTest {
        // Arrange
        val dummyPlayers = listOf(
            Player(
                id = 23,
                fullName = "Michael Jordan",
                position = "SG",
                height = "6'6\"",
                weight = "216 lbs",
                teamName = "Chicago Bulls"
            )
        )
        coEvery { repository.getPlayers() } returns flowOf(ApiResult.Success(dummyPlayers))

        // Act
        val result = useCase().toList()

        // Assert
        assertEquals(1, result.size)
        assertTrue(result[0] is ApiResult.Success)
        val players = (result[0] as ApiResult.Success).data
        assertEquals(1, players.size)
        assertEquals("Michael Jordan", players[0].fullName)
    }

    @Test
    fun `invoke should return flow of Error when repository emits error`() = runTest {
        // Arrange
        val errorMessage = "Unable to fetch players"
        coEvery { repository.getPlayers() } returns flowOf(ApiResult.Error(errorMessage))

        // Act
        val result = useCase().toList()

        // Assert
        assertEquals(1, result.size)
        assertTrue(result[0] is ApiResult.Error)
        val error = result[0] as ApiResult.Error
        assertEquals(errorMessage, error.message)
    }
}
