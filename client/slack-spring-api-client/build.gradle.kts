plugins {
    "java-library"
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.value("sources")
    from(sourceSets.main.get().allSource)
}

publishing {
    publications {
        register("slackSpringApiClient", MavenPublication::class) {
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
    compileOnly("org.springframework:spring-web")
    compileOnly("org.slf4j:slf4j-api")
    compileOnly("com.fasterxml.jackson.core:jackson-databind")
    //this dependency is need on consumer side but not provided by default spring so lets put it here
    api("com.fasterxml.jackson.module:jackson-module-kotlin")

    // https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient
    implementation("org.apache.httpcomponents:httpclient:4.5.9")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.slf4j:slf4j-api")
    testImplementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    testImplementation("org.springframework:spring-test") {
        exclude("junit:junit")
    }

    testImplementation(project(":slack-jackson-dto-test"))
}
