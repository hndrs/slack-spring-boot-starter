import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
        maven("http://repo.spring.io/plugins-release")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.41")
        classpath("io.spring.gradle:propdeps-plugin:0.0.9.RELEASE")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:0.9.18")
    }
}

plugins {
    id("org.sonarqube") version "2.7"
    id("io.spring.dependency-management") version "1.0.7.RELEASE"
    id("org.jetbrains.kotlin.jvm") version "1.3.41" apply false
    id("com.gradle.build-scan") version "2.2.1"
}

sonarqube {
    properties {
        property("sonar.projectName", "Slack Spring Boot Starter")
        property("sonar.projectKey", "com.kreait.slack-spring-boot-starter")
        property("sonar.host.url", "https://sonarcloud.io/")
        property("sonar.organization", "kreait")
        property("sonar.jacoco.report missing.force.zero", "true")
        property("sonar.pullrequest.provider", "github")
        property("sonar.exclusions", "**/slack-jackson-dto-test-extensions/**," +
                "**/slack-jackson-dto/**," +
                "**/samples/**, " +
                "**/SL4JLoggingReceiver.kt")
    }
}

extensions.findByName("buildScan")?.withGroovyBuilder {
    setProperty("termsOfServiceUrl", "https://gradle.com/terms-of-service")
    setProperty("termsOfServiceAgree", "yes")
}

allprojects {

    group = "com.kreait.slack"
    version = rootProject.file("version.txt").readText().trim()

    project.ext {
        set("junitJupiterVersion", "5.4.2")
        set("junitPlatformVersion", "1.4.2")
        set("springBootVersion", "2.1.3.RELEASE")
        set("projectVersion", version)
        set("kotlinVersion", "1.3.21")
    }

    tasks.withType<Wrapper> {
        gradleVersion = "5.2.1"
        // anything else
    }

    apply {
        plugin("io.spring.dependency-management")

    }


    dependencyManagement {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:${extra["springBootVersion"]}") {
                bomProperty("kotlin.version", "${extra["kotlinVersion"]}")
            }
        }
    }
}


subprojects {

    apply {
        plugin("eclipse")
        plugin("idea")
        plugin("kotlin")
        plugin("jacoco")
        plugin("maven-publish")
        plugin("propdeps")
        plugin("org.jetbrains.dokka")

    }

    val dokka by tasks.getting(DokkaTask::class) {
        moduleName = project.name
        outputFormat = "html"
        outputDirectory = "${rootProject.buildDir}/docs/$version/api"
    }

    configure<PublishingExtension> {
        repositories {
            maven {
                url = uri("s3://libs.olaph.io")
                credentials(AwsCredentials::class) {
                    accessKey = System.getenv("AWS_ACCESS_KEY_ID")
                    secretKey = System.getenv("AWS_SECRET_ACCESS_KEY")
                }
            }

        }
    }

    tasks {
        // Use the built-in JUnit support of Gradle.
        "test"(Test::class) {
            useJUnitPlatform()
        }
    }

    /**
     * Replace illegal characters from the module key
     */
    sonarqube {
        val regex = "(.*)/(.*)"
        val projectKey = project.name.replace(regex, "\$1:\$2")
        val sonarModuleKey = "${rootProject.group}:${rootProject.name}:$projectKey"
        properties {
            property("sonar.moduleKey", sonarModuleKey)
        }
    }


    tasks.withType<KotlinCompile>() {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }
    tasks.withType<KotlinCompile>() {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }
    configure<JacocoPluginExtension> {
        toolVersion = "0.8.3"
    }

    tasks.withType<JacocoReport> {
        reports {
            xml.apply {
                isEnabled = true
            }

        }
    }

    tasks.withType<Test>().configureEach {
        systemProperties(System.getenv())
        systemProperties["user.dir"] = workingDir
    }

    repositories {
        mavenCentral()
        jcenter()
    }

    dependencies {
        "testImplementation"("com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0")
        "testImplementation"(group = "org.junit.jupiter", name = "junit-jupiter", version = "${extra["junitJupiterVersion"]}")
    }

}

configurations.all {
    resolutionStrategy.cacheChangingModulesFor(0, TimeUnit.SECONDS)
}
