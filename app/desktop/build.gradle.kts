import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

kotlin {
    dependencies {
        implementation(compose.desktop.currentOs)
        implementation(Deps.ArkIvanov.Decompose.decompose)
        implementation(Deps.ArkIvanov.Decompose.extensionsCompose)
        implementation(Deps.ArkIvanov.MVIKotlin.mvikotlin)
        implementation(Deps.ArkIvanov.MVIKotlin.mvikotlinMain)
        implementation(Deps.Networking.KtorClient.desktop)
        implementation(Deps.DI.Kodein.di)
        implementation(Deps.JetBrains.Kotlin.Coroutines.core)
        implementation(Deps.JetBrains.Kotlin.Coroutines.jvm)


        implementation(project(":di"))
        implementation(project(":data:local"))
        implementation(project(":app:common"))
        implementation(project(":feature:root_screen:desktop"))
    }
}

compose.desktop {
    application {
        mainClass = "com.cat_breeds.desktop.app.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "CatBreeds"
            packageVersion = "1.0.0"

            modules("java.sql")

            windows {
                menuGroup = "Cat Breeds"
                // see https://wixtoolset.org/documentation/manual/v3/howtos/general/generate_guids.html
                upgradeUuid = "5452cda3-3f9d-48c0-a2d1-1127d3cd345c"
            }
        }
    }
}
