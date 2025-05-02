package com.android.basketballapp.presentation.games

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.basketballapp.data.Game
import com.android.basketballapp.data.utils.ApiResult
import com.android.domain.usecase.GetGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase
) : ViewModel() {

    private val _games = MutableStateFlow<ApiResult<List<Game>>>(ApiResult.Loading)
    val games: StateFlow<ApiResult<List<Game>>> = _games

    init {
        loadGames()
    }

    private fun loadGames() {
        viewModelScope.launch {
            getGamesUseCase().collect { result ->
                _games.value = result
            }
        }
    }
}
