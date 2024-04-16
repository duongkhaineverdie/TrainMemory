package com.emenike.trainmemory.di

import com.emenike.trainmemory.data.datastore.DataStoreManager
import com.emenike.trainmemory.data.repository.IRepository
import com.emenike.trainmemory.domain.interactors.GetHighScoreFromDSUseCase
import com.emenike.trainmemory.domain.interactors.SaveHighScoreDSUseCase
import com.emenike.trainmemory.domain.repository.RepositoryImpl
import com.emenike.trainmemory.presentation.ui.home.HomeViewModel
import com.emenike.trainmemory.presentation.ui.truecolorgame.TrainMemoryViewModel
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
    factory { GetHighScoreFromDSUseCase(get(), get()) }
    factory { SaveHighScoreDSUseCase(get(), get()) }
}

val repositoryModule = module {
    single<IRepository> { RepositoryImpl(get()) }
}
