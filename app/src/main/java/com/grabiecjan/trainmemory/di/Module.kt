package com.grabiecjan.trainmemory.di

import com.grabiecjan.trainmemory.data.datastore.DataStoreManager
import com.grabiecjan.trainmemory.data.repository.IRepository
import com.grabiecjan.trainmemory.domain.interactors.GetHighScoreFromDSUseCaseTrainMemory
import com.grabiecjan.trainmemory.domain.interactors.SaveHighScoreDSUseCaseTrainMemory
import com.grabiecjan.trainmemory.domain.repository.RepositoryImpl
import com.grabiecjan.trainmemory.presentation.ui.home.HomeViewModel
import com.grabiecjan.trainmemory.presentation.ui.trainmemorygame.TrainMemoryViewModel
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
