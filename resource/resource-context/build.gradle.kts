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
        create<MavenPublication>("resource-context") {
            groupId = "com.github.mmolosay"
            artifactId = "resource-context"
            version = "1.0.4"
            from(components["java"])
        }
    }
}
