import org.asciidoctor.gradle.AsciidoctorTask

buildscript {
    repositories {
        mavenCentral()
        maven("https://repo.spring.io/plugins-release")
    }
    dependencies {
        classpath("org.asciidoctor:asciidoctor-gradle-plugin:1.5.9.2")
    }
}

plugins {
    id("org.asciidoctor.convert") version "1.5.9.2"
}

tasks {
    "asciidoctor"(AsciidoctorTask::class) {
        requires("./libs/google-analytics.rb")
        sourceDir = file("src/main/asciidoc")
        outputDir = file("build/generated-docs")
    }
}
