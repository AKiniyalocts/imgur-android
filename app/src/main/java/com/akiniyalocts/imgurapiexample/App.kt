package com.akiniyalocts.imgurapiexample

import android.app.Application
import com.akiniyalocts.imgurapiexample.dependencies.appModule
import com.akiniyalocts.imgurapiexample.dependencies.networkModule
import com.akiniyalocts.imgurapiexample.dependencies.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Custom application class to initialize Koin.
 */
class App: Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule, networkModule, viewModelModule)
        }
    }
}