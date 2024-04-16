package com.emenike.trainmemory

import android.app.Application
import com.emenike.trainmemory.di.dataSourceModule
import com.emenike.trainmemory.di.dispatcherModule
import com.emenike.trainmemory.di.repositoryModule
import com.emenike.trainmemory.di.useCaseModule
import com.emenike.trainmemory.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                viewModelModule,
                dispatcherModule,
                dataSourceModule,
                useCaseModule,
                repositoryModule,
            )
        }
    }
}