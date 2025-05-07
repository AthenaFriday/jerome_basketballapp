package com.android.domain.usecase

import com.android.basketballapp.data.Team
import com.android.basketballapp.data.repository.TeamRepository
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
class GetTeamsUseCaseTest {

    private lateinit var repository: TeamRepository
    private lateinit var useCase: GetTeamsUseCase

    @Before
    fun setUp() {
        repository = mockk()
        useCase = GetTeamsUseCase(repository)
    }

    @Test
    fun `invoke should return flow of Success with teams list`() = runTest {
        // Arrange
        val dummyTeams = listOf(Team(id = 1, name = "Warriors", fullName = "Golden State Warriors", city = "San Francisco", conference = "West", division = "Pacific", abbreviation = "GSW"))
        coEvery { repository.getTeams() } returns flowOf(ApiResult.Success(dummyTeams))

        // Act
        val result = useCase().toList()

        // Assert
        assertEquals(1, result.size)
        assertTrue(result[0] is ApiResult.Success)
        val teams = (result[0] as ApiResult.Success).data
        assertEquals(1, teams.size)
        assertEquals("Warriors", teams[0].name)
    }

    @Test
    fun `invoke should return flow of Error when repository emits error`() = runTest {
        // Arrange
        val errorMessage = "Team service unavailable"
        coEvery { repository.getTeams() } returns flowOf(ApiResult.Error(errorMessage))

        // Act
        val result = useCase().toList()

        // Assert
        assertEquals(1, result.size)
        assertTrue(result[0] is ApiResult.Error)
        val error = result[0] as ApiResult.Error
        assertEquals(errorMessage, error.message)
    }
}
