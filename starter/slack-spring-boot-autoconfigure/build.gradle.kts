plugins { kotlin("kapt") }
apply {
    plugin("java-library")

}

extra["displayName"] = "Slack Spring Boot Autoconfigure"
description = "Contains autoconfiguration strategies for Slack Spring Boot"

dependencies {
    api(project(":slack-spring-boot"))

    implementation("org.springframework.boot:spring-boot")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-autoconfigure")
    api("org.springframework.boot:spring-boot-starter-actuator")
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    compileOnly("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude("junit:junit")
    }
    testImplementation("org.springframework.boot:spring-boot-starter-actuator")
    testImplementation("commons-io:commons-io:2.6")

}
