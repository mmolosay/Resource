import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.20"
}

group = "com.github.mmolosay"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
}

dependencies {
    // nothing is here
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
