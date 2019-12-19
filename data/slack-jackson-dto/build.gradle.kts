extra["displayName"] = "Slack Jackson Objects"
description = "Contains jackson implementation of slack api objects"

dependencies {
    compileOnly("com.fasterxml.jackson.core:jackson-annotations")
    compileOnly("com.fasterxml.jackson.core:jackson-databind")
    compileOnly(group = "com.fasterxml.jackson.module", name = "jackson-module-kotlin")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("com.jayway.jsonpath:json-path:2.4.0")
    testImplementation("org.springframework.boot:spring-boot-starter-web")
}
