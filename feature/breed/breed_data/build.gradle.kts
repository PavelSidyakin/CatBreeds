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
                implementation(Deps.Squareup.SQLDelight.extensions)

                implementation(project(":data:remote"))
                implementation(project(":data:local"))
                implementation(project(":feature:breed:breed_domain"))
            }
        }
    }
}
