package com.akiniyalocts.imgurapiexample.dependencies

import com.akiniyalocts.imgurapiexample.api.ImgurApi
import com.akiniyalocts.imgurapiexample.ui.MainViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.imgur.com")
            .client(get())
            .build()
    }

    single {
        OkHttpClient.Builder()
            .build()
    }

    single {
        get<Retrofit>().create(ImgurApi::class.java)
    }

}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}