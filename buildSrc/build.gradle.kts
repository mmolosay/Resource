plugins {
    `kotlin-dsl`
}

allprojects {

    repositories {
        gradlePluginPortal()
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
}
