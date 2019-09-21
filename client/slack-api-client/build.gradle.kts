plugins {
    "java-library"
}

extra["displayName"] = "Slack Api Client"
description = "Slack Api Client definition"

repositories {
    mavenCentral()
}

dependencies {
    api(project(":slack-jackson-dto"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
}
