pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "CatBreeds"

include(":di")

include(":feature:main_screen:android")
include(":feature:main_screen:desktop")

include(":feature:breed_info:breed_info_data")
include(":feature:breed_info:breed_info_domain")
include(":feature:breed_info:breed_info_ui")

include(":app:common")
include(":app:android")
include(":app:desktop")
