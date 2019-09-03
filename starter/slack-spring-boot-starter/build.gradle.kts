plugins { id("java-library") }


val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.value("sources")
    from(sourceSets.main.get().allSource)
}

publishing {
    publications {
        register("slackBrokerSpringBootStarter", MavenPublication::class) {
            from(components["java"])
            artifact(sourcesJar.get())
        }
    }
}

dependencies {
    api(project(":slack-spring-boot-autoconfigure"))
    api(project(":slack-spring-boot"))
    api(group = "org.springframework.boot", name = "spring-boot-starter-web")
    api(group = "com.fasterxml.jackson.module", name = "jackson-module-kotlin")

    implementation(group = "org.jetbrains.kotlin", name = "kotlin-stdlib-jdk8")
    implementation(group = "org.jetbrains.kotlin", name = "kotlin-reflect")
}
