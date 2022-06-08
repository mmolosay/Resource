plugins {
    id("resource.kotlin-application-conventions")
}

dependencies {
    implementation(project(":resource-context"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")
}
