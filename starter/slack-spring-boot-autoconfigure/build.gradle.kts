plugins { kotlin("kapt") }
apply {
    plugin("java-library")

}


val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.value("sources")
    from(sourceSets.main.get().allSource)
}

publishing {
    publications {
        register("slackBrokerSpringBootAutoconfigure", MavenPublication::class) {
            from(components["java"])
            artifact(sourcesJar.get())
        }
    }
}
dependencies {
    api(project(":slack-spring-boot"))

    implementation("org.springframework.boot:spring-boot")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-autoconfigure")
    optional("org.springframework.boot:spring-boot-starter-actuator")
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    compileOnly("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.springframework.boot:spring-boot-starter-test:${extra["springBootVersion"]}") {
        exclude("junit:junit")
    }
    testImplementation("org.springframework.boot:spring-boot-starter-actuator")
    testImplementation("commons-io:commons-io:2.6")

}
