package com.emenike.trainmemory.presentation.ui.trainmemorygame

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emenike.trainmemory.domain.interactors.GetHighScoreFromDSUseCaseTrainMemory
import com.emenike.trainmemory.domain.interactors.SaveHighScoreDSUseCaseTrainMemory
import com.emenike.trainmemory.utils.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.random.Random

class TrainMemoryViewModel(
    savedStateHandle: SavedStateHandle,
    val getHighScoreFromDSUseCase: GetHighScoreFromDSUseCaseTrainMemory,
    val saveHighScoreDSUseCase: SaveHighScoreDSUseCaseTrainMemory,
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<TrainMemoryUiState> =
        MutableStateFlow(TrainMemoryUiState())

    val stateFlow: StateFlow<TrainMemoryUiState> = _stateFlow.asStateFlow()

    init {
        getHighScoreFromDS()
        renderListCorrect()
        viewModelScope.launch {
            val timeDelay = 100L
            while (isActive) {
                if (stateFlow.value.timeAlive > 0L && !stateFlow.value.isDefeat) {
                    val timeLeft = stateFlow.value.timeAlive - timeDelay
                    if (timeLeft > 0) {
                        _stateFlow.update {
                            it.copy(
                                timeAlive = stateFlow.value.timeAlive - timeDelay,
                            )
                        }
                    } else {
                        _stateFlow.update {
                            it.copy(
                                isDefeat = true,
                            )
                        }
                    }
                }
                delay(timeDelay)
            }
        }
    }

    fun selectItemCorrect(indexButton: Int) {
        val levelGame = stateFlow.value.levelGame
        val timeAliveAfter = when (val totalTimeAlive = stateFlow.value.totalAlive) {
            in 8000..10000 -> {
                totalTimeAlive - 200
            }

            in 5000..8000 -> totalTimeAlive - 100
            else -> {
                totalTimeAlive
            }
        }
        if (stateFlow.value.listCorrect.contains(indexButton)) {
            val listSelected = stateFlow.value.listSelected + indexButton
            if (listSelected.size == stateFlow.value.listCorrect.size) {
                _stateFlow.update {
                    it.copy(
                        levelGame = levelGame + 1,
                        timeAlive = timeAliveAfter,
                        totalAlive = timeAliveAfter
                    )
                }
                renderListCorrect()
            } else {
                _stateFlow.update {
                    it.copy(
                        listSelected = listSelected
                    )
                }
            }
        } else {
            _stateFlow.update {
                it.copy(
                    isDefeat = true,
                )
            }
        }
    }

    fun nextLevels() {
        val highScore = stateFlow.value.levelGame - 1
        if (highScore > stateFlow.value.highScore) {
            saveHighScoreDS(highScore)
        }
        _stateFlow.update {
            it.copy(
                levelGame = 2,
                isDefeat = false,
                timeAlive = 10000,
                totalAlive = 10000
            )
        }
        renderListCorrect()
    }

    private fun randomColor() {
        val color = Constants.randomColor()
        _stateFlow.update {
            it.copy(
                color = color,
            )
        }
    }

    private fun getHighScoreFromDS() {
        viewModelScope.launch {
            getHighScoreFromDSUseCase(Unit).collectLatest { result ->
                result.onSuccess { value ->
                    _stateFlow.update {
                        it.copy(
                            highScore = value
                        )
                    }
                }
            }
        }
    }

    private fun saveHighScoreDS(score: Int) {
        viewModelScope.launch {
            saveHighScoreDSUseCase(score).onSuccess {
                getHighScoreFromDS()
            }
        }
    }

    private fun renderListCorrect() {
        val levelGame = stateFlow.value.levelGame
        val maxNumberItemCorrect = levelGame - 1
        val listCorrect: ArrayList<Int> = arrayListOf()
        while (listCorrect.size != maxNumberItemCorrect) {
            val randomNumber = Random.nextInt(0, levelGame)
            if (!listCorrect.contains(randomNumber)) {
                listCorrect.add(randomNumber)
            }
        }
        _stateFlow.update {
            it.copy(
                listCorrect = listCorrect,
                listSelected = arrayListOf()
            )
        }
        randomColor()
    }
}

data class TrainMemoryUiState(
    val levelGame: Int = 2,
    val timeAlive: Long = 10000,
    val totalAlive: Long = 10000,
    val highScore: Int = 0,
    val color: Int = 0,
    val isDefeat: Boolean = false,
    val listCorrect: List<Int> = arrayListOf(),
    val listSelected: List<Int> = arrayListOf(),
)