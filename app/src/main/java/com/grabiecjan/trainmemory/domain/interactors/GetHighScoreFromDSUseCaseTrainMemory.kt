package com.grabiecjan.trainmemory.domain.interactors

import com.grabiecjan.trainmemory.data.repository.IRepository
import com.grabiecjan.trainmemory.domain.interactors.type.BaseUseCaseFlowTrainMemory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class GetHighScoreFromDSUseCaseTrainMemory(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCaseFlowTrainMemory<Unit, Int>(dispatcher) {
    override suspend fun build(param: Unit): Flow<Int> = repository.getHighScoreFromDS()

}