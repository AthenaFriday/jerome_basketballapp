package com.android.basketballapp.presentation.players

import app.cash.turbine.test
import com.android.basketballapp.data.Player
import com.android.basketballapp.data.utils.ApiResult
import com.android.domain.usecase.GetPlayersUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PlayersViewModelTest {

    private lateinit var useCase: GetPlayersUseCase
    private lateinit var viewModel: PlayersViewModel

    @Before
    fun setUp() {
        useCase = mockk()
    }

    @Test
    fun players_state_should_emit_Success_when_use_case_returns_players() = runTest {
        // Arrange
        val dummyPlayers = listOf(
            Player(
                id = 30,
                fullName = "Stephen Curry",
                position = "PG",
                height = "6'2\"",
                weight = "185 lbs",
                teamName = "Warriors"
            )
        )
        coEvery { useCase() } returns flowOf(ApiResult.Success(dummyPlayers))

        viewModel = PlayersViewModel(useCase)

        // Act & Assert
        viewModel.players.test {
            val result = awaitItem()
            assertTrue(result is ApiResult.Success<*>)
            val players = (result as ApiResult.Success<List<Player>>).data
            assertEquals(1, players.size)
            assertEquals("Stephen Curry", players[0].fullName)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun players_state_should_emit_Error_when_use_case_returns_error() = runTest {
        // Arrange
        val errorMessage = "Failed to load players"
        coEvery { useCase() } returns flowOf(ApiResult.Error(errorMessage))

        viewModel = PlayersViewModel(useCase)

        // Act & Assert
        viewModel.players.test {
            val result = awaitItem()
            assertTrue(result is ApiResult.Error)
            val error = result as ApiResult.Error
            assertEquals(errorMessage, error.message)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
