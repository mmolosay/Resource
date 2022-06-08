plugins {
    id("resource.kotlin-common-conventions")
    `java-library`
    `maven-publish`
}

configure<PublishingExtension> {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
            from(components["java"])
        }
    }
}
