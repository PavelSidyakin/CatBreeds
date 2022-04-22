plugins {
    id("multiplatform-setup")
    id("android-setup")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(Deps.DI.Kodein.di)
                implementation(project(":feature:breed_info:breed_info_domain"))
                implementation(project(":data:remote"))
            }
        }
    }
}
