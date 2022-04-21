plugins {
    id("multiplatform-setup")
    id("android-setup")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(Deps.DI.Kodein.di)
                implementation(Deps.JetBrains.Kotlin.Coroutines.core)
            }
        }
    }
}
