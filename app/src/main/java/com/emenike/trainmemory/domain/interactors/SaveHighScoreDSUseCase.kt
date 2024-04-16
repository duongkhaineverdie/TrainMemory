package com.emenike.trainmemory.domain.interactors

import com.emenike.trainmemory.data.repository.IRepository
import com.emenike.trainmemory.domain.interactors.type.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher

class SaveHighScoreDSUseCase(
    private val repository: IRepository,
    dispatcher: CoroutineDispatcher,
) : BaseUseCase<Int, Unit>(dispatcher) {
    override suspend fun block(param: Int): Unit = repository.saveHighScoreDS(score = param)
}