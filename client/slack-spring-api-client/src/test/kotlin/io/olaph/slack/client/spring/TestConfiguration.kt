package io.olaph.slack.client.spring

object TestConfig {

    fun token(): String = System.getProperty("slack.test.integration.token")
    fun buildNumber(): String = System.getProperty("slack.test.integration.build")
}
