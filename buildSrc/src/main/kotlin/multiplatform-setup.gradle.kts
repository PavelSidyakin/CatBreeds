plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
}

initDeps(project)

kotlin {
    jvm("desktop")
    android()
    ios()

    js(IR) {
        browser()
    }

    sourceSets {
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}
