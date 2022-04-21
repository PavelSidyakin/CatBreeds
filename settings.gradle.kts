pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "CatBreeds"

include(":di")

include(":data:remote")

include(":feature:root_screen:android")
include(":feature:root_screen:desktop")
include(":feature:root_screen:common")

include(":feature:breed_list:breed_list_data")
include(":feature:breed_list:breed_list_domain")
include(":feature:breed_list:breed_list_ui")

include(":feature:breed_info:breed_info_data")
include(":feature:breed_info:breed_info_domain")
include(":feature:breed_info:breed_info_ui")

include(":app:common")
include(":app:android")
include(":app:desktop")
