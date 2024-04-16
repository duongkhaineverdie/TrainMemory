package com.emenike.trainmemory.data.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.asLiveData
import com.emenike.trainmemory.utils.TAG
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// At the top level of your kotlin file:
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "train_memory")

class DataStoreManager(private val context: Context) {
    init {
        context
    }

    companion object {
        val HIGH_SCORE = intPreferencesKey("HIGH_SCORE")
    }

    suspend fun storeHighScore(score: Int) {
        Log.d(TAG, "storeHighScore: $score")
        context.dataStore.edit {
            it[HIGH_SCORE] = score
        }
    }

    val highScore: Flow<Int> = context.dataStore.data.map {
        it[HIGH_SCORE] ?: 0
    }
}