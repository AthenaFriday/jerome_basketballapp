package com

import com.android.basketballapp.data.remote.api.TeamApi
import com.android.basketballapp.data.remote.mappers.TeamDto
import com.android.basketballapp.data.remote.mappers.TeamResponseDto
import com.android.basketballapp.data.repository.TeamRepositoryImpl
import com.android.basketballapp.data.utils.ApiResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TeamRepositoryImplTest {

    private lateinit var api: TeamApi
    private lateinit var repository: TeamRepositoryImpl

    @Before
    fun setUp() {
        api = mockk()
        repository = TeamRepositoryImpl(api)
    }

    @Test
    fun `getTeams should emit Loading and then Success when API call succeeds`() = runTest {
        // Arrange
        val dto = TeamDto(id = 10, abbreviation = "LAL", fullName = "Los Angeles Lakers", city = "Los Angeles", conference = "West", division = "Pacific", name = "Lakers")
        val response = TeamResponseDto(data = listOf(dto))
        coEvery { api.getTeams() } returns response

        // Act
        val emissions = repository.getTeams().toList()

        // Assert
        assertEquals(ApiResult.Loading, emissions[0])
        assertTrue(emissions[1] is ApiResult.Success)
        val teams = (emissions[1] as ApiResult.Success).data
        assertEquals(1, teams.size)
        assertEquals("Lakers", teams[0].name)
    }

    @Test
    fun `getTeams should emit Loading and then Error when API call fails`() = runTest {
        // Arrange
        val errorMessage = "Failed to fetch teams"
        coEvery { api.getTeams() } throws RuntimeException(errorMessage)

        // Act
        val emissions = repository.getTeams().toList()

        // Assert
        assertEquals(ApiResult.Loading, emissions[0])
        assertTrue(emissions[1] is ApiResult.Error)
        val error = emissions[1] as ApiResult.Error
        assertEquals(errorMessage, error.message)
    }
}
