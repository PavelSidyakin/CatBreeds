plugins {
    id("multiplatform-setup")
    id("android-setup")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(":di"))
                implementation(project(":feature:breed_info:breed_info_domain"))
                implementation(Deps.DI.Kodein.di)
            }
        }
    }
}
