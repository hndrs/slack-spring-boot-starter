plugins { "java-library" }


val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.value("sources")
    from(sourceSets.main.get().allSource)
}

publishing {
    publications {
        register("slackJacksonDtoTestExtension", MavenPublication::class) {
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
    compileOnly("org.slf4j:slf4j-api")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    compile("org.springframework.boot:spring-boot-starter-web")
}
