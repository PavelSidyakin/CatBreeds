import org.jetbrains.compose.compose

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

kotlin {
    dependencies {
        implementation(compose.desktop.currentOs)
        implementation(Deps.ArkIvanov.Decompose.decompose)
        implementation(Deps.ArkIvanov.Decompose.extensionsCompose)
        implementation(Deps.JetBrains.Kotlin.Coroutines.core)
        implementation(Deps.JetBrains.Kotlin.Coroutines.jvm)
        implementation(Deps.DI.Kodein.di)

        implementation(project(":di"))
        implementation(project(":feature:root_screen:common"))

        implementation(project(":feature:breed_list:breed_list_ui"))
        implementation(project(":feature:breed_info:breed_info_ui"))
        implementation(project(":resources"))
    }
}
