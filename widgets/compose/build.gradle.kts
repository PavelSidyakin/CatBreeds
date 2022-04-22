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
                implementation(compose.runtime)
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
            }
        }

        val androidMain by getting {
            dependencies {
                Deps.AndroidX.Compose.default.forEach { implementation(it) }
                implementation(Deps.Widgets.landscapistGlide)
            }
        }
    }
}
