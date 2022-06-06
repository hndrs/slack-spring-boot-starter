plugins { "java-library" }

repositories {
    mavenCentral()
}

extra["displayName"] = "Slack Jackson Objects Extensions"
description = "Contains extension functions that allow convenient object creation in tests"

dependencies {
    api(project(":slack-jackson-dto"))
    compileOnly("org.slf4j:slf4j-api")
    compileOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    compileOnly("org.springframework.boot:spring-boot-starter-web")
}
