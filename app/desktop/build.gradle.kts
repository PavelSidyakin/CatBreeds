import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform") // kotlin("jvm") doesn't work well in IDEA/AndroidStudio (https://github.com/JetBrains/compose-jb/issues/22)
    id("org.jetbrains.compose")
}

kotlin {
    jvm {
        withJava()
    }

    sourceSets {
        named("jvmMain") {
            dependencies {
                implementation(compose.desktop.currentOs)
//                implementation(project(":common:utils"))
//                implementation(project(":common:database"))
//                implementation(project(":common:root"))
//                implementation(project(":common:compose-ui"))
                implementation(Deps.ArkIvanov.Decompose.decompose)
                implementation(Deps.ArkIvanov.Decompose.extensionsCompose)
                implementation(Deps.ArkIvanov.MVIKotlin.mvikotlin)
                implementation(Deps.ArkIvanov.MVIKotlin.mvikotlinMain)
               // implementation(Deps.Badoo.Reaktive.reaktive)
               // implementation(Deps.Badoo.Reaktive.coroutinesInterop)
                implementation(Deps.Networking.KtorClient.desktop)
                implementation(Deps.DI.Kodein.di)

                implementation(project(":di"))
                implementation(project(":app:common"))
                implementation(project(":feature:main_screen:desktop"))
            }
        }
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
