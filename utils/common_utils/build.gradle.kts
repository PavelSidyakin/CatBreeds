plugins {
    id("multiplatform-setup")
    id("android-setup")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Deps.DI.Kodein.di)
                implementation(Deps.JetBrains.Kotlin.Coroutines.core)
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation(Deps.JetBrains.Kotlin.Coroutines.jvm)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(Deps.JetBrains.Kotlin.Coroutines.android)
            }
        }
    }
}
