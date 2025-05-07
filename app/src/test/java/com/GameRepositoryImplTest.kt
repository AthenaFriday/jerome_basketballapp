package com

import com.android.basketballapp.data.remote.GameResponseDto
import com.android.basketballapp.data.remote.api.GameApi
import com.android.basketballapp.data.remote.mappers.GameDto
import com.android.basketballapp.data.remote.mappers.MetaDto
import com.android.basketballapp.data.remote.mappers.TeamDto
import com.android.basketballapp.data.repository.GameRepositoryImpl
import com.android.basketballapp.data.utils.ApiResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GameRepositoryImplTest {

    private lateinit var api: GameApi
    private lateinit var repository: GameRepositoryImpl

    @Before
    fun setUp() {
        api = mockk()
        repository = GameRepositoryImpl(api)
    }

    @Test
    fun `getGames should emit Loading and then Success when API call succeeds`() = runTest {
        // Arrange
        val teamDto = TeamDto(
            id = 1,
            abbreviation = "LAL",
            city = "Los Angeles",
            conference = "West",
            division = "Pacific",
            fullName = "Los Angeles Lakers",
            name = "Lakers"
        )

        val dto = GameDto(
            id = 1,
            date = "2025-05-01",
            homeTeam = teamDto,
            visitorTeam = teamDto,
            homeTeamScore = 100,
            visitorTeamScore = 98,
            season = 2025,
            period = 4,
            status = "Final",
            time = "20:00",
            postseason = false
        )

        val meta = MetaDto(
            totalPages = 1,
            currentPage = 1,
            nextPage = null,
            perPage = 25,
            totalCount = 1
        )

        val response = GameResponseDto(data = listOf(dto), meta = meta)
        coEvery { api.getGames() } returns response

        // Act
        val emissions = repository.getGames().toList()

        // Assert
        assertEquals(ApiResult.Loading, emissions[0])
        assertTrue(emissions[1] is ApiResult.Success)
        val games = (emissions[1] as ApiResult.Success).data
        assertEquals(1, games.size)
        assertEquals(1, games[0].id)
    }

    @Test
    fun `getGames should emit Loading and then Error when API call throws exception`() = runTest {
        // Arrange
        val errorMessage = "Network error"
        coEvery { api.getGames() } throws RuntimeException(errorMessage)

        // Act
        val emissions = repository.getGames().toList()

        // Assert
        assertEquals(ApiResult.Loading, emissions[0])
        assertTrue(emissions[1] is ApiResult.Error)
        val error = emissions[1] as ApiResult.Error
        assertEquals(errorMessage, error.message)
    }
}
