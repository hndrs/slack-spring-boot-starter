import io.gitlab.arturbosch.detekt.Detekt
import io.hndrs.publish.meta.Contributor
import io.hndrs.publish.meta.Developer
import io.hndrs.publish.meta.License
import io.hndrs.publish.meta.Organization
import io.hndrs.publish.meta.Scm
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        jcenter()
        mavenCentral()
        maven("https://repo.spring.io/plugins-release")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.20")
        classpath("io.spring.gradle:propdeps-plugin:0.0.9.RELEASE")
    }
}

plugins {
    id("org.sonarqube") version "3.1.1"
    id("io.spring.dependency-management") version "1.0.7.RELEASE"
    id("org.jetbrains.kotlin.jvm") version "1.4.20" apply false
    id("org.jetbrains.dokka") version "1.4.20"
    id("io.gitlab.arturbosch.detekt") version "1.14.2"
    signing
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

allprojects {

    val isRelease: String? by project

    repositories {
        mavenCentral()
        jcenter()
    }

    apply {
        from("${rootProject.rootDir}/publish-meta.gradle.kts")
    }

    group = "io.hndrs"
    version = rootProject.file("version.txt").readText().trim()
        .plus(if (isRelease?.toBoolean() == true) "" else "-SNAPSHOT")

    project.ext {
        set("junitJupiterVersion", "5.4.2")
        set("junitPlatformVersion", "1.4.2")
        set("projectVersion", version)
    }

    tasks.withType<Wrapper> {
        gradleVersion = "6.7.1"
        // anything else
    }

    apply {
        plugin("io.spring.dependency-management")
    }


    dependencyManagement {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:2.4.0") {
                bomProperty("kotlin.version", "1.4.20")
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
        plugin("propdeps")
        plugin("io.gitlab.arturbosch.detekt")
        plugin("signing")
        plugin("org.jetbrains.dokka")
    }

    afterEvaluate {
        configure<PublishingExtension> {
            val projectsNotToPublish = listOf<Project>(project(":slack-spring-boot-starter-sample"), project(":slack-spring-boot-docs"))
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
                        pom {
                            if (project.extra.has("displayName")) {
                                name.set(project.extra["displayName"] as? String)
                            }
                            description.set(project.description)
                            pom {
                                url.set("https://github.com/hndrs/slack-spring-boot-starter")
                                (extra["organization"] as Organization).let {
                                    this.organization {
                                        name.set(it.name)
                                        url.set(it.url)
                                    }
                                }

                                licenses {
                                    (extra["license"] as License).let {
                                        this.license {
                                            name.set(it.name)
                                            url.set(it.url)
                                        }
                                    }
                                }
                                developers {
                                    (extra["developers"] as List<Developer>).forEach {
                                        this.developer {
                                            id.set(it.id)
                                            name.set(it.name)
                                            email.set(it.email)
                                        }
                                    }
                                }
                                contributors {
                                    (extra["contributors"] as List<Contributor>).forEach {
                                        this.contributor {
                                            name.set(it.name)
                                            email.set(it.email)
                                        }
                                    }
                                }
                                (extra["scm"] as Scm).let {
                                    this.scm {
                                        connection.set(it.connection)
                                        url.set(it.url)
                                    }
                                }
                            }
                        }
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
            jvmTarget = "11"
        }
    }
    tasks.withType<KotlinCompile>() {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }
    configure<JacocoPluginExtension> {
        toolVersion = "0.8.6"
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
