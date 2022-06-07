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
            artifactId = "resource-plain"
            version = "1.0.3"
            from(components["java"])
        }
    }
}
