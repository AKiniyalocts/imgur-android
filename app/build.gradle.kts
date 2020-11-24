
plugins {
    id ("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(AndroidSdk.compile)

    defaultConfig {
        applicationId = "com.akiniyalocts.imgurapiexample"
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = 1
        versionName = "1.0"

    }

    buildFeatures {
        dataBinding = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }

    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xallow-result-return-type",
            "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xuse-experimental=kotlinx.coroutines.FlowPreview")
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = listOf("-Xallow-jvm-ir-dependencies", "-Xskip-prerelease-check",
            "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
    }
}

dependencies {

    implementation(Kotlin.kotlin)
    implementation(Kotlin.coroutines)
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.constraintLayout)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.lifecycle)

    implementation(Material.materialComponents)

    implementation(Koin.core)
    implementation(Koin.android)
    implementation(Koin.androidViewModel)

    implementation(Retrofit.retrofit)
    implementation(Retrofit.retrofitConverterMoshi)
    implementation(OkHttp.okhttp)
    implementation(OkHttp.okhttpLogging)

    implementation(Moshi.moshi)
    kapt(Moshi.moshiCodeGen)

    implementation(PermissionsDispatcher.permissions)

}