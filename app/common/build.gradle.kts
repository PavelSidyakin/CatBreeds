plugins {
    id("multiplatform-setup")
    id("android-setup")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(Deps.DI.Kodein.di)
                implementation(project(":di"))

                implementation(project(":feature:breed_info:breed_info_data"))
                implementation(project(":feature:breed_info:breed_info_domain"))
                implementation(project(":feature:breed_info:breed_info_ui"))

            }
        }
    }
}
