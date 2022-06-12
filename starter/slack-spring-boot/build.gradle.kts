extra["displayName"] = "Slack Spring Boot"
description = "Spring Boot integration for Slack applications"

dependencies {
    api("com.slack.api:slack-api-client:1.22.1")

    compileOnly("org.springframework.boot:spring-boot-starter-logging")
    compileOnly("org.springframework.boot:spring-boot-starter-web")
    compileOnly("io.micrometer:micrometer-core")

    implementation("commons-codec:commons-codec:1.15")
    implementation("commons-io:commons-io:2.11.0")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.3")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("io.micrometer:micrometer-core")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude("junit:junit")
    }
}
