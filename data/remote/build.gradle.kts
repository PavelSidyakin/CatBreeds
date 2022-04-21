import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING

buildscript {
    dependencies {
        classpath("com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:0.11.0")
    }
}

plugins {
    id("multiplatform-setup")
    id("android-setup")
    kotlin("plugin.serialization") version "1.6.10"
    id("com.codingfeline.buildkonfig") version "0.11.0"
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Deps.DI.Kodein.di)
                implementation(Deps.JetBrains.Kotlin.Coroutines.core)
                implementation(Deps.Networking.KtorClient.core)
                implementation(Deps.Networking.KtorClient.contentNegotiation)
                implementation(Deps.Networking.KtorClient.serializationKotlinxJson)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(Deps.JetBrains.Kotlin.Coroutines.android)
                implementation(Deps.Networking.KtorClient.android)
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(Deps.JetBrains.Kotlin.Coroutines.jvm)
                implementation(Deps.Networking.KtorClient.desktop)
            }
        }
    }
}

buildkonfig {
    packageName = "com.cat_breeds.cat_api"
    // objectName = "YourAwesomeConfig"
    // exposeObjectWithName = "YourAwesomePublicConfig"

    defaultConfigs {
        buildConfigField(STRING, "CAT_API_KEY", properties["catApiKey"].toString())
    }
}
