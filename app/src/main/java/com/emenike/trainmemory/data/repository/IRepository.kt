package com.emenike.trainmemory.data.repository

import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getHighScoreFromDS(): Flow<Int>
    suspend fun saveHighScoreDS(score: Int)
}