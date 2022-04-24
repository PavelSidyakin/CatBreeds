// We store Kotlin and Compose versions in gradle.properties to
// be able to override them on CI.
// You probably won't need this, so you can get rid of `project` in this file.
import org.gradle.api.Project

lateinit var properties: Map<String, *>

fun initDeps(project: Project) {
    properties = project.properties
}

object Deps {
    object DI {
        object Kodein {
            private const val VERSION = "7.10.0"

            const val di = "org.kodein.di:kodein-di:$VERSION"
        }
    }

    object JetBrains {
        object Kotlin {
            private val VERSION get() = properties["kotlin.version"]

            val gradlePlugin get() = "org.jetbrains.kotlin:kotlin-gradle-plugin:$VERSION"
            val testCommon get() = "org.jetbrains.kotlin:kotlin-test-common:$VERSION"
            val testJunit get() = "org.jetbrains.kotlin:kotlin-test-junit:$VERSION"
            val testJs get() = "org.jetbrains.kotlin:kotlin-test-js:$VERSION"
            val testAnnotationsCommon get() = "org.jetbrains.kotlin:kotlin-test-annotations-common:$VERSION"

            object Coroutines {
                private const val VERSION = "1.6.1"
                const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$VERSION"
                const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$VERSION"
                const val jvm = "org.jetbrains.kotlinx:kotlinx-coroutines-swing:$VERSION"
            }
        }


        object Compose {
            private val VERSION get() = properties["compose.version"]

            val gradlePlugin get() = "org.jetbrains.compose:compose-gradle-plugin:$VERSION"
        }
    }

    object Android {
        object Tools {
            object Build {
                const val gradlePlugin = "com.android.tools.build:gradle:7.0.4"
            }
        }
    }

    object AndroidX {
        object AppCompat {
            const val appCompat = "androidx.appcompat:appcompat:1.3.0"
        }

        object Compose {
            private const val VERSION = "1.1.1"

            const val ui = "androidx.compose.ui:ui:$VERSION"
            const val tooling = "androidx.compose.ui:ui:$VERSION"
            const val foundation = "androidx.compose.foundation:foundation:$VERSION"
            const val material = "androidx.compose.material:material:$VERSION"
            const val activity = "androidx.activity:activity-compose:1.4.0"
            const val iconsCore = "androidx.compose.material:material-icons-core:$VERSION"
            const val iconsEx = "androidx.compose.material:material-icons-extended:$VERSION"

            val default = listOf(ui, tooling, foundation, material, activity, iconsCore, iconsEx)
        }

        object Accompanist {
            private const val VERSION = "0.23.1"

            const val swipeRefresh = "com.google.accompanist:accompanist-swiperefresh:$VERSION"
        }
    }

    object ArkIvanov {
        object MVIKotlin {
            private const val VERSION = "3.0.0-beta01"

            const val mvikotlin = "com.arkivanov.mvikotlin:mvikotlin:$VERSION"
            const val mvikotlinCoroutines = "com.arkivanov.mvikotlin:mvikotlin-extensions-coroutines:$VERSION"
            const val mvikotlinMain = "com.arkivanov.mvikotlin:mvikotlin-main:$VERSION"
            const val mvikotlinLogging = "com.arkivanov.mvikotlin:mvikotlin-logging:$VERSION"
            const val mvikotlinTimeTravel = "com.arkivanov.mvikotlin:mvikotlin-timetravel:$VERSION"
        }

        object Decompose {
            private const val VERSION = "0.6.0"

            const val decompose = "com.arkivanov.decompose:decompose:$VERSION"
            const val extensionsCompose = "com.arkivanov.decompose:extensions-compose-jetbrains:$VERSION"
        }

        object Essenty {
            private const val VERSION = "0.2.2"

            const val lifecycle = "com.arkivanov.essenty:lifecycle:$VERSION"
        }
    }

    object Icerock {
        object Resources {
            const val VERSION = "0.19.0"
            const val core = "dev.icerock.moko:resources:$VERSION"
            const val generator = "dev.icerock.moko:resources-generator:$VERSION"
            const val compose = "dev.icerock.moko:resources-compose:$VERSION"
        }
    }

    object Squareup {
        object SQLDelight {
            const val VERSION = "1.5.3"

            const val gradlePlugin = "com.squareup.sqldelight:gradle-plugin:$VERSION"
            const val extensions = "com.squareup.sqldelight:coroutines-extensions:$VERSION"

            const val androidDriver = "com.squareup.sqldelight:android-driver:$VERSION"
            const val sqliteDriver = "com.squareup.sqldelight:sqlite-driver:$VERSION"
            const val nativeDriver = "com.squareup.sqldelight:native-driver:$VERSION"
            const val sqljsDriver = "com.squareup.sqldelight:sqljs-driver:$VERSION"
        }
    }

    object Networking {
        object KtorClient {
            private const val VERSION = "2.0.0"

            const val core = "io.ktor:ktor-client-core:$VERSION"
            const val contentNegotiation = "io.ktor:ktor-client-content-negotiation:$VERSION"
            const val serializationKotlinxJson = "io.ktor:ktor-serialization-kotlinx-json:$VERSION"
            const val android = "io.ktor:ktor-client-okhttp:$VERSION"
            const val desktop = "io.ktor:ktor-client-cio:$VERSION"
        }
    }

    object Widgets {
        const val landscapistGlide = "com.github.skydoves:landscapist-glide:1.5.1"
    }
}
