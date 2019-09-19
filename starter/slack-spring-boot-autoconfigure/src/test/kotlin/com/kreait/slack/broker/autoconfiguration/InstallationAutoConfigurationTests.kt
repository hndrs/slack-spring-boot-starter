package com.kreait.slack.broker.autoconfiguration

import com.kreait.slack.broker.broker.InstallationBroker
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration

@DisplayName("Test Installation Auto configuration")
class InstallationAutoConfigurationTests {

    private val contextRunner = TestApplicationContext.base()
            .withSystemProperties(
                    "slack.installation.error-redirect-url:http://localhost:8080/installation/error",
                    "slack.installation.success-redirect-url:http://localhost:8080/installation/success"
            )
            .withConfiguration(AutoConfigurations.of(SlackBrokerAutoConfiguration::class.java, TeamStoreAutoconfiguration::class.java, WebMvcAutoConfiguration::class.java))

    @DisplayName("InstallationBroker Registration")
    @Test
    fun installationBrokerRegistration() {
        contextRunner.run {
            Assertions.assertDoesNotThrow { it.getBean(InstallationBroker::class.java) }
        }
    }
}
