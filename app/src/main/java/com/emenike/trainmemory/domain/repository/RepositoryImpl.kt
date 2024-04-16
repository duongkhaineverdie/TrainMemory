package com.emenike.trainmemory.domain.repository

import com.emenike.trainmemory.data.datastore.DataStoreManager
import com.emenike.trainmemory.data.repository.IRepository
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val dataStoreManager: DataStoreManager,
): IRepository {
    override fun getHighScoreFromDS(): Flow<Int> = dataStoreManager.highScore

    override suspend fun saveHighScoreDS(score: Int) = dataStoreManager.storeHighScore(score)
}