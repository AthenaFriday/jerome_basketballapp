package com.android.basketballapp.data.repository

import com.android.basketballapp.data.remote.api.PlayerApi
import com.android.basketballapp.data.remote.mappers.MetaDto
import com.android.basketballapp.data.remote.mappers.PlayerDto
import com.android.basketballapp.data.remote.mappers.PlayerResponseDto
import com.android.basketballapp.data.remote.mappers.TeamDto
import com.android.basketballapp.data.utils.ApiResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class PlayerRepositoryImplTest {

    private lateinit var api: PlayerApi
    private lateinit var repository: PlayerRepositoryImpl

    @Before
    fun setUp() {
        api = mockk()
        repository = PlayerRepositoryImpl(api)
    }

    @Test
    fun `getPlayers should emit Loading and then Success when API call succeeds`() = runTest {
        // Arrange
        val teamDto = TeamDto(
            id = 10,
            abbreviation = "LAL",
            city = "Los Angeles",
            conference = "West",
            division = "Pacific",
            fullName = "Los Angeles Lakers",
            name = "Lakers"
        )

        val dto = PlayerDto(
            id = 42,
            first_name = "LeBron",
            last_name = "James",
            position = "SF",
            height_feet = 6,
            height_inches = 9,
            weight_pounds = 250,
            team = teamDto
        )

        val meta = MetaDto(
            totalPages = 1,
            currentPage = 1,
            nextPage = null,
            perPage = 25,
            totalCount = 1
        )

        val response = PlayerResponseDto(
            data = listOf(dto),
            meta = meta
        )

        coEvery { api.getPlayers() } returns response

        // Act
        val emissions = repository.getPlayers().toList()

        // Assert
        assertEquals(ApiResult.Loading, emissions[0])
        assertTrue(emissions[1] is ApiResult.Success)
        val players = (emissions[1] as ApiResult.Success).data
        assertEquals(1, players.size)
        assertEquals("LeBron James", players[0].fullName) // assuming your toDomain() maps first + last name
    }

    @Test
    fun `getPlayers should emit Loading and then Error when API call fails`() = runTest {
        // Arrange
        val errorMessage = "API failure"
        coEvery { api.getPlayers() } throws RuntimeException(errorMessage)

        // Act
        val emissions = repository.getPlayers().toList()

        // Assert
        assertEquals(ApiResult.Loading, emissions[0])
        assertTrue(emissions[1] is ApiResult.Error)
        val error = emissions[1] as ApiResult.Error
        assertEquals(errorMessage, error.message)
    }
}
