package com.grabiecjan.trainmemory.domain.repository

import com.grabiecjan.trainmemory.data.datastore.DataStoreManager
import com.grabiecjan.trainmemory.data.repository.IRepository
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val dataStoreManager: DataStoreManager,
): IRepository {
    override fun getHighScoreFromDS(): Flow<Int> = dataStoreManager.highScore

    override suspend fun saveHighScoreDS(score: Int) = dataStoreManager.storeHighScore(score)
}