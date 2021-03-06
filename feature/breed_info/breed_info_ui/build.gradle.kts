import org.jetbrains.compose.compose

plugins {
    id("multiplatform-setup")
    id("android-setup")
    id("org.jetbrains.compose")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Deps.ArkIvanov.MVIKotlin.mvikotlin)
                implementation(Deps.ArkIvanov.MVIKotlin.mvikotlinMain)
                implementation(Deps.ArkIvanov.MVIKotlin.mvikotlinCoroutines)
                implementation(Deps.ArkIvanov.Decompose.decompose)
                implementation(Deps.ArkIvanov.Decompose.extensionsCompose)
                implementation(Deps.DI.Kodein.di)
                implementation(Deps.JetBrains.Kotlin.Coroutines.core)
                implementation(compose.runtime)

                implementation(project(":di"))
                implementation(project(":utils:common_utils"))
                implementation(project(":feature:breed_info:breed_info_domain"))
                implementation(project(":widgets:compose"))
                implementation(project(":utils:presentation_utils"))
                implementation(project(":resources"))
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation(Deps.JetBrains.Kotlin.Coroutines.jvm)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(Deps.JetBrains.Kotlin.Coroutines.android)
                Deps.AndroidX.Compose.default.forEach { implementation(it) }
                implementation(Deps.Widgets.landscapistGlide)
            }
        }
    }
}
