plugins {
    id("multiplatform-setup")
    id("android-setup")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Deps.DI.Kodein.di)
                implementation(Deps.JetBrains.Kotlin.Coroutines.core)

                implementation(project(":di"))

                implementation(project(":data:remote"))
                implementation(project(":data:local"))

                implementation(project(":feature:breed:breed_domain"))
                implementation(project(":feature:breed:breed_data"))

                implementation(project(":feature:breed_info:breed_info_domain"))
                implementation(project(":feature:breed_info:breed_info_ui"))

                implementation(project(":feature:breed_list:breed_list_domain"))
                implementation(project(":feature:breed_list:breed_list_ui"))

                implementation(project(":feature:root_screen:common"))

            }
        }
    }
}
