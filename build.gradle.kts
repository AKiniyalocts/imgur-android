// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}")
    }
}
allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven(url="http://oss.jfrog.org/artifactory/oss-snapshot-local/")
        maven(url = "https://dl.bintray.com/ekito/koin")
        maven(url = "https://jitpack.io")
    }
}