
// TeamsViewModel.kt
package com.android.basketballapp.teams

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.basketballapp.data.Team
import com.android.basketballapp.data.utils.ApiResult
import com.android.domain.usecase.GetTeamsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val getTeamsUseCase: GetTeamsUseCase
) : ViewModel() {

    private val _teams = MutableStateFlow<ApiResult<List<Team>>>(ApiResult.Loading)
    val teams: StateFlow<ApiResult<List<Team>>> = _teams

    init {
        loadTeams()
    }

    private fun loadTeams() {
        viewModelScope.launch {
            getTeamsUseCase().collect { result ->
                _teams.value = result
            }
        }
    }
}