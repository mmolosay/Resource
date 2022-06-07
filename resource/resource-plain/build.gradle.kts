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
        create<MavenPublication>("resource-plain") {
            groupId = "com.github.mmolosay.resource"
            artifactId = "resource-plain"
            version = "1.0.4"
            from(components["java"])
        }
    }
}
