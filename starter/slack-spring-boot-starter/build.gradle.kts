plugins { id("java-library") }

extra["displayName"] = "Slack Spring Boot Starter"
description = "Spring Boot Starter package that aggregates and autoconfigures a slack application"

dependencies {
    api(project(":slack-spring-boot-autoconfigure"))
    api(project(":slack-spring-boot"))
    api(group = "org.springframework.boot", name = "spring-boot-starter-web")
    api(group = "com.fasterxml.jackson.module", name = "jackson-module-kotlin")

    implementation(group = "org.jetbrains.kotlin", name = "kotlin-stdlib-jdk8")
    implementation(group = "org.jetbrains.kotlin", name = "kotlin-reflect")
}
