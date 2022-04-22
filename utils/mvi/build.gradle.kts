import org.jetbrains.compose.compose

plugins {
    id("multiplatform-setup")
    id("android-setup")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Deps.ArkIvanov.MVIKotlin.mvikotlin)
                implementation(Deps.ArkIvanov.MVIKotlin.mvikotlinMain)
                implementation(Deps.ArkIvanov.MVIKotlin.mvikotlinCoroutines)
                implementation(Deps.ArkIvanov.Decompose.decompose)
                implementation(Deps.JetBrains.Kotlin.Coroutines.core)
            }
        }
    }
}
