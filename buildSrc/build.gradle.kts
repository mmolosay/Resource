plugins {
    `kotlin-dsl`
}

allprojects {

    repositories {
        gradlePluginPortal() // TODO: remove if unused
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
}
