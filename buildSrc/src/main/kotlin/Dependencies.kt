object AndroidSdk {
    const val min = 21
    const val compile = 30
    const val target = compile
}
object Versions {
    const val kotlin = "1.4.10"
    const val kotlinCoroutines = "1.3.9-native-mt-2"
    const val koin = "3.0.0-alpha-4"

    const val material = "1.3.0-alpha03"
    const val coreKtx = "1.3.1"
    const val constraintLayout = "1.1.3"
    const val appCompat = "1.2.0"
    const val lifeCycle = "2.2.0"

    const val retrofit = "2.9.0"
    const val retrofitLogging = "4.2.1"

    const val moshi = "1.9.3"

    const val coil = "1.0.0"
}
object Kotlin{
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"
}
object AndroidX{
    val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifeCycle}"
}

object Material{
    val materialComponents = "com.google.android.material:material:${Versions.material}"
}

object Koin {
    val core = "org.koin:koin-core:${Versions.koin}"
    val android = "org.koin:koin-android:${Versions.koin}"
    val androidViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
}

object Retrofit{
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofitConverterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    val retrofitLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.retrofitLogging}"
}

object Moshi{
    val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    val moshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
}

object Coil{
    val coil = "io.coil-kt:coil:${Versions.coil}"
}
