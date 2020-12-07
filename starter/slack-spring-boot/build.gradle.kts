plugins { "java-library" }

extra["displayName"] = "Slack Spring Boot"
description = "Spring Boot integration for Slack applications"

dependencies {
    api(project(":slack-jackson-dto"))
    api(project(":slack-spring-api-client"))

    compileOnly("org.springframework.boot:spring-boot-starter-logging")
    compileOnly("org.springframework.boot:spring-boot-starter-web")
    compileOnly("io.micrometer:micrometer-core")

    implementation("commons-codec:commons-codec:1.12")
    implementation("commons-io:commons-io:2.6")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("io.micrometer:micrometer-core")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude("junit:junit")
    }
    testImplementation(project(":slack-jackson-dto-test"))
    testImplementation(project(":slack-spring-test-api-client"))
}
