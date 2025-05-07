package com

import app.cash.turbine.test
import com.android.basketballapp.data.Team
import com.android.basketballapp.data.utils.ApiResult
import com.android.basketballapp.teams.TeamsViewModel
import com.android.domain.usecase.GetTeamsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class TeamsViewModelTest {

    private lateinit var useCase: GetTeamsUseCase
    private lateinit var viewModel: TeamsViewModel

    @Before
    fun setUp() {
        useCase = mockk()
    }

    @Test
    fun teams_state_should_emit_Success_when_use_case_returns_teams() = runTest {
        // Arrange
        val dummyTeams = listOf(
            Team(
                id = 1,
                name = "Heat",
                fullName = "Miami Heat",
                city = "Miami",
                conference = "East",
                division = "Southeast",
                abbreviation = "MIA"
            )
        )
        coEvery { useCase() } returns flowOf(ApiResult.Success(dummyTeams))

        // Act
        viewModel = TeamsViewModel(useCase)

        // Assert
        viewModel.teams.test {
            val result = awaitItem()
            assertTrue(result is ApiResult.Success<*>)
            val teams = (result as ApiResult.Success<List<Team>>).data
            assertEquals(1, teams.size)
            assertEquals("Heat", teams[0].name)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun teams_state_should_emit_Error_when_use_case_returns_error() = runTest {
        // Arrange
        val errorMessage = "Error loading teams"
        coEvery { useCase() } returns flowOf(ApiResult.Error(errorMessage))

        // Act
        viewModel = TeamsViewModel(useCase)

        // Assert
        viewModel.teams.test {
            val result = awaitItem()
            assertTrue(result is ApiResult.Error)
            val error = result as ApiResult.Error
            assertEquals(errorMessage, error.message)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
