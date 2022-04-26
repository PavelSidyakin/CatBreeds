plugins {
    id("multiplatform-setup")
    id("android-setup")
    id("io.kotest.multiplatform") version Deps.UnitTesting.Kotest.VERSION
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Deps.DI.Kodein.di)
                implementation(Deps.JetBrains.Kotlin.Coroutines.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(Deps.UnitTesting.Kotest.engine)
                implementation(Deps.UnitTesting.Kotest.assertions)
                implementation(Deps.UnitTesting.Mockk.mockkCommon)
            }
        }

        val desktopTest by getting {
            dependencies {
                implementation(Deps.UnitTesting.Kotest.runnerJvm)
                implementation(Deps.UnitTesting.Mockk.mockk)
                implementation(Deps.UnitTesting.Mockk.mockkJvm)
            }
        }
    }
}
