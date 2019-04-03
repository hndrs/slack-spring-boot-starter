package io.olaph.slack.client.spring

import org.slf4j.Logger
import org.slf4j.LoggerFactory

object TestConfig {

    fun token(): String = System.getProperty("slack.test.integration.token")
    fun buildNumber(): String = System.getProperty("slack.test.integration.build")
}

val TEST_LOG: Logger = LoggerFactory.getLogger("TestLogger")
