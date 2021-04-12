package com.example.testtask

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import com.example.testtask.domain.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModules)
        }
    }
}
