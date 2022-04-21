plugins {
    id("multiplatform-setup")
    id("android-setup")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(Deps.DI.Kodein.di)
                implementation(Deps.JetBrains.Kotlin.Coroutines.core)

                implementation(project(":di"))
                implementation(project(":data:remote"))
                implementation(project(":feature:breed_list:breed_list_domain"))
            }
        }
    }
}
