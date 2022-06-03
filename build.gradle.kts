import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.20"
    `java-library`
    `maven-publish`
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

publishing {
    publications {
        register("mavenJava", MavenPublication::class) {
            groupId = "com.github.mmolosay"
            artifactId = "resource"
//            version = ""
            from(components["java"])
        }
    }
}
