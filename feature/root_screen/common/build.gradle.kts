plugins {
    id("multiplatform-setup")
    id("android-setup")
    id("kotlin-parcelize")
    id("io.kotest.multiplatform") version Deps.UnitTesting.Kotest.VERSION
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Deps.ArkIvanov.MVIKotlin.mvikotlin)
                implementation(Deps.ArkIvanov.MVIKotlin.mvikotlinMain)
                implementation(Deps.ArkIvanov.MVIKotlin.mvikotlinCoroutines)
                implementation(Deps.ArkIvanov.Decompose.decompose)
                implementation(Deps.DI.Kodein.di)
                implementation(Deps.JetBrains.Kotlin.Coroutines.core)

                implementation(project(":di"))
                implementation(project(":feature:breed_list:breed_list_ui"))
                implementation(project(":feature:breed_info:breed_info_ui"))
                implementation(project(":utils:presentation_utils"))
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
