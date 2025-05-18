package com.passakorn.novels

import android.app.Application
import com.passakorn.novels.di.NovelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NovelApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NovelApplication)
            modules(NovelModules.allModules)
        }
    }
}