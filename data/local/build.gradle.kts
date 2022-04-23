buildscript {
    dependencies {
        classpath(Deps.Squareup.SQLDelight.gradlePlugin)
    }
}

plugins {
    id("multiplatform-setup")
    id("android-setup")
    id("com.squareup.sqldelight")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Deps.DI.Kodein.di)
                implementation(Deps.JetBrains.Kotlin.Coroutines.core)
                implementation(Deps.Squareup.SQLDelight.extensions)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(Deps.JetBrains.Kotlin.Coroutines.android)
                implementation(Deps.Squareup.SQLDelight.androidDriver)
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(Deps.JetBrains.Kotlin.Coroutines.jvm)
                implementation(Deps.Squareup.SQLDelight.sqliteDriver)
            }
        }
    }
}

sqldelight {
    database("CatBreedsDatabase") {
        packageName = "com.cat_breeds.data.db"
    }
}
