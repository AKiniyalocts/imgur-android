package com.akiniyalocts.imgurapiexample.dependencies

import com.akiniyalocts.imgurapiexample.api.ImgurApi
import com.akiniyalocts.imgurapiexample.repo.UploadRepository
import com.akiniyalocts.imgurapiexample.repo.UploadRepositoryImp
import com.akiniyalocts.imgurapiexample.ui.MainViewModel
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * A Koin module encapsulating app-based dependencies.
 */
val appModule = module {

    single {
        androidContext().contentResolver
    }

    single<UploadRepository> {
        UploadRepositoryImp(get(), get())
    }
}

/**
 * A Koin module encapsulating network-based dependencies.
 */
val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.imgur.com")
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }

    single {
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)// timeouts were happening on a few test devices
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        }
    }

    single {
        get<Retrofit>().create(ImgurApi::class.java)
    }

    single {
        Moshi.Builder()
            .build()
    }

}

/**
 * A Koin module encapsulating viewmodel dependencies.
 */
val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}