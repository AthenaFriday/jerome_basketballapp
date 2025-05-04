// PlayersViewModel.kt
package com.android.basketballapp.presentation.players

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.basketballapp.data.Player
import com.android.basketballapp.data.utils.ApiResult
import com.android.domain.usecase.GetPlayersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(
    private val getPlayersUseCase: GetPlayersUseCase
) : ViewModel() {

    private val _players = MutableStateFlow<ApiResult<List<Player>>>(ApiResult.Loading)
    val players: StateFlow<ApiResult<List<Player>>> = _players

    init {
        loadPlayers()
    }

    private fun loadPlayers() {
        viewModelScope.launch {
            getPlayersUseCase().collect { result ->
                _players.value = result
            }
        }
    }
}