package com.emenike.trainmemory.di

import com.emenike.trainmemory.data.datastore.DataStoreManager
import com.emenike.trainmemory.data.repository.IRepository
import com.emenike.trainmemory.domain.interactors.GetHighScoreFromDSUseCaseTrainMemory
import com.emenike.trainmemory.domain.interactors.SaveHighScoreDSUseCaseTrainMemory
import com.emenike.trainmemory.domain.repository.RepositoryImpl
import com.emenike.trainmemory.presentation.ui.home.HomeViewModel
import com.emenike.trainmemory.presentation.ui.trainmemorygame.TrainMemoryViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val viewModelModule = module {
    single { HomeViewModel(get()) }
    factory { TrainMemoryViewModel(get(), get(), get()) }
}

val dispatcherModule = module {
    factory { Dispatchers.Default }
}

val dataSourceModule = module {
    single { DataStoreManager(get()) }
}

val useCaseModule = module {
    factory { GetHighScoreFromDSUseCaseTrainMemory(get(), get()) }
    factory { SaveHighScoreDSUseCaseTrainMemory(get(), get()) }
}

val repositoryModule = module {
    single<IRepository> { RepositoryImpl(get()) }
}
