package com.kreait.slack.sample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SampleApplication

fun main(args: Array<String>) {
    System.setProperty("http.proxyHost", "127.0.0.1");
    System.setProperty("http.proxyPort", "8888");
    runApplication<SampleApplication>(*args)
}
