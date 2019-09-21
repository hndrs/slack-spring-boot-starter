import com.kreait.publish.meta.Contributor
import com.kreait.publish.meta.Developer
import com.kreait.publish.meta.License
import com.kreait.publish.meta.Organization
import com.kreait.publish.meta.Scm
import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        jcenter()
        mavenCentral()
        maven("http://repo.spring.io/plugins-release")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.41")
        classpath("io.spring.gradle:propdeps-plugin:0.0.9.RELEASE")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:0.9.18")
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.0.1")
    }
}

plugins {
    id("org.sonarqube") version "2.7"
    id("io.spring.dependency-management") version "1.0.7.RELEASE"
    id("org.jetbrains.kotlin.jvm") version "1.3.41" apply false
    id("com.gradle.build-scan") version "2.2.1"
    signing
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

    apply {
        from("${rootProject.rootDir}/publish-meta.gradle.kts")
    }

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
        gradleVersion = "5.4.1"
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
        plugin("io.gitlab.arturbosch.detekt")
        plugin("signing")

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
        if (project != project(":slack-spring-boot-starter-sample")) {
            publications {
                val sourcesJar by tasks.registering(Jar::class) {
                    archiveClassifier.value("sources")
                    from(project.the<SourceSetContainer>()["main"].allSource)
                }
                create(project.name, MavenPublication::class) {
                    from(components["java"])
                    artifact(sourcesJar.get())
                    pom {
                        if (project.extra.has("displayName")) {
                            name.set(project.extra["displayName"] as? String)
                        }
                        description.set(project.description)
                        pom {
                            url.set("http://www.example.com/library2")
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
            signing {
                val signingKey: String? by project
                val signingPassword: String? by project
                useInMemoryPgpKeys(signingKey, signingPassword)
                sign(publications[project.name])
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

    tasks.withType<Sign>().configureEach {
        onlyIf {
            extra.has("isRelease") && extra["isRelease"] as Boolean
        }
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
        systemProperties(System.getenv())
        systemProperties["user.dir"] = workingDir
    }

    tasks.withType<Detekt>().configureEach {
        config = files("${rootProject.rootDir}/detekt.yml")
        // setSource(files("**/src/main/java", "**/src/main/kotlin"))
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
