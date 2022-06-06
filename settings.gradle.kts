rootProject.name = "SlackSpringBoot"

//starter
include("slack-spring-boot-starter")
project(":slack-spring-boot-starter").projectDir = File("starter/slack-spring-boot-starter")
include("slack-spring-boot-autoconfigure")
project(":slack-spring-boot-autoconfigure").projectDir = File("starter/slack-spring-boot-autoconfigure")
include("slack-spring-boot")
project(":slack-spring-boot").projectDir = File("starter/slack-spring-boot")

//data libraries
include("slack-jackson-dto")
project(":slack-jackson-dto").projectDir = File("data/slack-jackson-dto")
include("slack-jackson-dto-test")
project(":slack-jackson-dto-test").projectDir = File("data/slack-jackson-dto-test-extensions")

//Clients
include("slack-api-client")
project(":slack-api-client").projectDir = File("client/slack-api-client")
include("slack-spring-api-client")
project(":slack-spring-api-client").projectDir = File("client/slack-spring-api-client")
include("slack-spring-test-api-client")
project(":slack-spring-test-api-client").projectDir = File("client/slack-spring-test-api-client")

//Samples
include("slack-spring-boot-starter-sample")
project(":slack-spring-boot-starter-sample").projectDir = File("samples/slack-spring-boot-starter-sample")

pluginManagement {
    val kotlinVersion: String by settings

    plugins {
        id("io.spring.dependency-management").version("1.0.11.RELEASE")
        kotlin("jvm").version(kotlinVersion)
        kotlin("plugin.spring").version(kotlinVersion)
        kotlin("kapt").version(kotlinVersion)
        id("maven-publish")
        id("idea")
        id("org.jetbrains.dokka").version(kotlinVersion)
        id("io.hndrs.publishing-info").version("2.0.0")
    }
    repositories {
    }
}
