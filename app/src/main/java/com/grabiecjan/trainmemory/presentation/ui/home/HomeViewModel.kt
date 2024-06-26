package com.grabiecjan.trainmemory.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grabiecjan.trainmemory.domain.interactors.GetHighScoreFromDSUseCaseTrainMemory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    val getHighScoreFromDSUseCase: GetHighScoreFromDSUseCaseTrainMemory,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getHighScoreFromDSUseCase(Unit).collectLatest {
                it.onSuccess {highScore ->
                    _uiState.update { state ->
                        state.copy(
                            highScore = highScore
                        )
                    }
                }
            }
        }
    }
}

data class HomeUiState(
    val highScore: Int = 0,
)