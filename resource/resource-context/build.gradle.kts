plugins {
    kotlin("jvm")
    `java-library`
    `maven-publish`
}

dependencies {
    // nothing is here
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.github.mmolosay"
            artifactId = "resource-context"
            version = "1.0.0"
            from(components["java"])
        }
    }
}
