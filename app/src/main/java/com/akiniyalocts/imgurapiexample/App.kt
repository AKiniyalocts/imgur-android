package com.akiniyalocts.imgurapiexample

import android.app.Application
import com.akiniyalocts.imgurapiexample.dependencies.appModule
import com.akiniyalocts.imgurapiexample.dependencies.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)

            modules(appModule, viewModelModule)
        }
    }
}