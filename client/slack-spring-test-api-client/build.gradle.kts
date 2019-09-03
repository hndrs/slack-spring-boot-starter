plugins {
    "java-library"
}


val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.value("sources")
    from(sourceSets.main.get().allSource)
}

publishing {
    publications {
        register("slackSpringTestApi", MavenPublication::class) {
            from(components["java"])
            artifact(sourcesJar.get())
        }
    }
}

repositories {
    mavenCentral()
}

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

