package com.grabiecjan.trainmemory.domain.interactors

import com.grabiecjan.trainmemory.data.repository.IRepository
import com.grabiecjan.trainmemory.domain.interactors.type.BaseUseCaseTrainMemory
import kotlinx.coroutines.CoroutineDispatcher

class SaveHighScoreDSUseCaseTrainMemory(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCaseTrainMemory<Int, Unit>(dispatcher) {
    override suspend fun block(param: Int): Unit = repository.saveHighScoreDS(score = param)
}