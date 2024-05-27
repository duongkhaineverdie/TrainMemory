package com.grabiecjan.trainmemory

import android.app.Application
import com.grabiecjan.trainmemory.di.dataSourceModule
import com.grabiecjan.trainmemory.di.dispatcherModule
import com.grabiecjan.trainmemory.di.repositoryModule
import com.grabiecjan.trainmemory.di.useCaseModule
import com.grabiecjan.trainmemory.di.viewModelModule
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