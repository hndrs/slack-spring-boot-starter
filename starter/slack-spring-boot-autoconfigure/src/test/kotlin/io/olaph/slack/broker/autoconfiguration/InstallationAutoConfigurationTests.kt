package io.olaph.slack.broker.autoconfiguration

import io.olaph.slack.broker.broker.InstallationBroker
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.boot.test.context.runner.ApplicationContextRunner

@DisplayName("Test Installation Auto configuration")
class InstallationAutoConfigurationTests {

    private val contextRunner = ApplicationContextRunner()
            .withSystemProperties(
                    "slack.installation.error-redirect-url:http://localhost:8080/installation/error",
                    "slack.installation.success-redirect-url:http://localhost:8080/installation/success",
                    "slack.installation.clientId:123",
                    "slack.installation.clientSecret:1234"
            )
            .withConfiguration(AutoConfigurations.of(SlackBrokerAutoConfiguration::class.java, WebMvcAutoConfiguration::class.java))

    @DisplayName("InstallationBroker Registration")
    @Test
    fun installationBrokerRegistration() {
        contextRunner.run {
            Assertions.assertDoesNotThrow { it.getBean(InstallationBroker::class.java) }
        }
    }
}