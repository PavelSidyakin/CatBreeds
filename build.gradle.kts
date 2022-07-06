plugins {
    `kotlin-dsl`
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
        maven {
            url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        }
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }
}
