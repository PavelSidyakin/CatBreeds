buildscript {
    repositories {
        gradlePluginPortal()
    }

    dependencies {
        classpath(Deps.Icerock.Resources.generator)
    }
}

plugins {
    id("multiplatform-setup")
    id("android-setup")
    id("dev.icerock.mobile.multiplatform-resources").version(Deps.Icerock.Resources.VERSION)
}

multiplatformResources {
    multiplatformResourcesPackage = "com.cat_breeds.resources"
    multiplatformResourcesClassName = "SharedRes"
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(Deps.Icerock.Resources.core)
            }
        }
        val androidMain by getting {
            dependencies {
                api(Deps.Icerock.Resources.compose)
            }
        }
        val desktopMain by getting {
            dependencies {
                api(Deps.Icerock.Resources.compose)
            }
        }
    }
}
