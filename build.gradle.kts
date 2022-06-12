import io.gitlab.arturbosch.detekt.Detekt
import io.hndrs.gradle.plugin.Developer
import io.hndrs.gradle.plugin.License
import io.hndrs.gradle.plugin.Organization
import io.hndrs.gradle.plugin.Scm
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        jcenter()
        mavenCentral()
    }
}

val kotlinVersion: String by extra

plugins {
    id("org.sonarqube") version "3.3"
    id("io.spring.dependency-management")
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.dokka")
    id("io.gitlab.arturbosch.detekt") version "1.18.1"
    signing
    id("io.hndrs.publishing-info")
}

sonarqube {
    properties {
        property("sonar.projectName", "Slack Spring Boot Starter")
        property("sonar.projectKey", "hndrs_slack-spring-boot-starter")
        property("sonar.host.url", "https://sonarcloud.io/")
        property("sonar.organization", "hndrs")
        property("sonar.jacoco.report missing.force.zero", "true")
        property("sonar.pullrequest.provider", "github")
        property(
            "sonar.exclusions", "**/slack-jackson-dto-test-extensions/**," +
                    "**/slack-jackson-dto/**," +
                    "**/samples/**, " +
                    "**/slack-api-client/**, " +
                    "**/SL4JLoggingReceiver.kt"
        )
    }
}

publishingInfo {
    inceptionYear = "2019"
    url = "\"https://github.com/hndrs/slack-spring-boot-starter"
    license = License(
        "https://github.com/hndrs/gradle-publishing-info-plugin/blob/main/LICENSE",
        "MIT License"
    )
    developers = listOf(
        Developer("marvinschramm", "Marvin Schramm", "marvin.schramm@gmail.com"),
    )
    contributers = listOf()
    organization = Organization("hndrs", "https://oss.hndrs.io")
    scm = Scm("scm:git:git://github.com/hndrs/slack-spring-boot-starter.git", "https://github.com/hndrs/slack-spring-boot-starter")
}

allprojects {

    val isRelease: String? by project

    repositories {
        mavenCentral()
        jcenter()
    }

    group = "io.hndrs.slack"
    version = rootProject.file("version.txt").readText().trim()
        .plus(if (isRelease?.toBoolean() == true) "" else "-SNAPSHOT")
    println(version)
    project.ext {
        set("junitJupiterVersion", "5.4.2")
        set("junitPlatformVersion", "1.4.2")
        set("projectVersion", version)
    }

    tasks.withType<Wrapper> {
        gradleVersion = "7.4.2"
        // anything else
    }

    apply {
        plugin("io.spring.dependency-management")
    }


    dependencyManagement {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:2.7.0") {
                bomProperty("kotlin.version", "$kotlinVersion")
            }
        }
    }
}


subprojects {

    val isRelease: String? by project
    val rawVersion = rootProject.file("version.txt").readText().trim()

    apply {
        plugin("eclipse")
        plugin("idea")
        plugin("kotlin")
        plugin("jacoco")
        plugin("maven-publish")
        plugin("io.gitlab.arturbosch.detekt")
        plugin("signing")
        plugin("org.jetbrains.dokka")
    }

    afterEvaluate {
        configure<PublishingExtension> {
            val projectsNotToPublish = listOf<Project>(project(":slack-spring-boot-starter-sample"))
            repositories {
                maven {
                    name = "snapshot"
                    url = uri("https://oss.sonatype.org/content/repositories/snapshots")
                    credentials {
                        username = System.getenv("SONATYPE_USER")
                        password = System.getenv("SONATYPE_PASSWORD")
                    }
                }
                maven {
                    name = "release"
                    url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2")
                    credentials {
                        username = System.getenv("SONATYPE_USER")
                        password = System.getenv("SONATYPE_PASSWORD")
                    }
                }
            }

            // only create publications for relevant projects
            if (!projectsNotToPublish.contains(project)) {
                publications {

                    val sourcesJar by tasks.registering(Jar::class) {
                        archiveClassifier.set("sources")
                        from(project.the<SourceSetContainer>()["main"].allSource)
                    }

                    val javaDocJar by tasks.registering(Jar::class) {
                        archiveClassifier.set("javadoc")
                        from(tasks.dokkaJavadoc.get().outputDirectory.get())
                    }

                    create(project.name, MavenPublication::class) {
                        from(components["java"])
                        artifact(sourcesJar.get())
                        artifact(javaDocJar.get())
                    }
                }
                val signingKey: String? = System.getenv("SIGNING_KEY")
                val signingPassword: String? = System.getenv("SIGNING_PASSWORD")
                if (signingKey != null && signingPassword != null) {
                    signing {
                        useInMemoryPgpKeys(groovy.json.StringEscapeUtils.unescapeJava(signingKey), signingPassword)
                        sign(publications[project.name])
                    }
                }
            }

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
            property("sonar.kotlin.detekt.reportPaths", "${project.buildDir}/reports/detekt/detekt.xml")
        }
    }

    tasks.withType<KotlinCompile>() {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = JavaVersion.VERSION_17.majorVersion
        }
    }

    tasks.withType<KotlinCompile>() {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = JavaVersion.VERSION_17.majorVersion
        }
    }
    configure<JacocoPluginExtension> {
        toolVersion = "0.8.8"
    }

    tasks.withType<JacocoReport> {
        reports {
            xml.apply {
                isEnabled = true
            }

        }
    }

    //region Release settings
    tasks.withType<Sign>().configureEach {
        onlyIf {
            isRelease?.toBoolean() ?: false
        }
    }

    tasks.withType<PublishToMavenRepository>().configureEach {
        val release = isRelease?.toBoolean() ?: false
        onlyIf {
            (repository.name == "snapshot" && !release)
                    ||
                    (repository.name == "release" && release)
        }
    }
    //endregion

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
        systemProperties(System.getenv())
        systemProperties["user.dir"] = workingDir
    }

    tasks.withType<Detekt> {
        this.ignoreFailures = true
        autoCorrect = true
        config.setFrom(files("${rootProject.rootDir}/detekt.yml"))
    }

    dependencies {
        "testImplementation"("com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0")
        "testImplementation"(group = "org.junit.jupiter", name = "junit-jupiter", version = "5.4.2")
    }
}

configurations.all {
    resolutionStrategy.cacheChangingModulesFor(0, TimeUnit.SECONDS)
}
