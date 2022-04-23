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

                implementation(project(":feature:breed:breed_domain"))
            }
        }
    }
}
