package com.emenike.trainmemory.domain.interactors

import com.emenike.trainmemory.data.repository.IRepository
import com.emenike.trainmemory.domain.interactors.type.BaseUseCaseFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class GetHighScoreFromDSUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCaseFlow<Unit, Int>(dispatcher) {
    override suspend fun build(param: Unit): Flow<Int> = repository.getHighScoreFromDS()

}