package com.saleh.githubsocial

import android.app.Application
import com.saleh.core.di.databaseModule
import com.saleh.core.di.networkModule
import com.saleh.core.di.repositoryModule
import com.saleh.githubsocial.di.useCaseModule
import com.saleh.githubsocial.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}