package com.akiniyalocts.imgurapiexample.dependencies

import com.akiniyalocts.imgurapiexample.api.ImgurApi
import com.akiniyalocts.imgurapiexample.repo.UploadRepository
import com.akiniyalocts.imgurapiexample.repo.UploadRepositoryImp
import com.akiniyalocts.imgurapiexample.ui.MainViewModel
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.imgur.com")
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }

    single {
        OkHttpClient.Builder()
            .build()
    }

    single {
        get<Retrofit>().create(ImgurApi::class.java)
    }

    single {
        androidContext().contentResolver
    }

    single {
        Moshi.Builder()
            .build()
    }

    single<UploadRepository> {
        UploadRepositoryImp(get(), get())
    }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}