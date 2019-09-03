val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.value("sources")
    from(sourceSets.main.get().allSource)
}

publishing {
    publications {
        register("slackJacksonDto", MavenPublication::class) {
            from(components["java"])
            artifact(sourcesJar.get())
        }
    }
}



dependencies {
    compileOnly("com.fasterxml.jackson.core:jackson-annotations")
    compileOnly("com.fasterxml.jackson.core:jackson-databind")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("com.jayway.jsonpath:json-path:2.4.0")
    testImplementation("org.springframework.boot:spring-boot-starter-web")
}
