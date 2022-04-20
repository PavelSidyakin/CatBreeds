plugins {
    id("multiplatform-setup")
    //id("org.jetbrains.kotlin.multiplatform")
    id("android-setup")
    //kotlin("multiplatform")
}

kotlin {
    //android()
    //jvm()
    sourceSets {
//        val commonMain by getting {
//            dependencies {
//                implementation(Deps.DI.Kodein.di)
//            }
//        }
        named("commonMain") {
            dependencies {
                implementation(Deps.DI.Kodein.di)
            }
        }
    }
}
