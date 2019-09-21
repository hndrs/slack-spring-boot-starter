plugins {
    "java-library"
}

repositories {
    mavenCentral()
}

extra["displayName"] = "Slack Test Api Client"
description = "Test Implementation of an Slack Api Client with mocking features to allow unit testing"

dependencies {
    api(project(":slack-jackson-dto"))
    api(project(":slack-api-client"))
    compileOnly("org.slf4j:slf4j-api")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation(project(":slack-jackson-dto-test"))
    testImplementation("org.mockito:mockito-all:2.0.2-beta")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0")

}

