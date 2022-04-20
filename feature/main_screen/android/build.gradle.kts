import org.jetbrains.compose.compose

plugins {
    id("com.android.library")
    kotlin("android")
    id("org.jetbrains.compose")
}

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 23
        targetSdk = 31
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    packagingOptions {
        exclude("META-INF/*")
    }
}

dependencies {
//    implementation(project(":common:database"))
//    implementation(project(":common:utils"))
//    implementation(project(":common:root"))
//    implementation(project(":common:compose-ui"))
    implementation(compose.material)
    implementation(Deps.ArkIvanov.MVIKotlin.mvikotlin)
    implementation(Deps.ArkIvanov.MVIKotlin.mvikotlinMain)
    implementation(Deps.ArkIvanov.MVIKotlin.mvikotlinLogging)
    implementation(Deps.ArkIvanov.MVIKotlin.mvikotlinTimeTravel)
    implementation(Deps.ArkIvanov.Decompose.decompose)
    implementation(Deps.ArkIvanov.Decompose.extensionsCompose)
    implementation(Deps.AndroidX.AppCompat.appCompat)
    implementation(Deps.AndroidX.Activity.activityCompose)
    implementation(Deps.DI.Kodein.di)

    implementation(project(":di"))
    // TODO: remove
    implementation(project(":feature:breed_info:breed_info_domain"))
}
