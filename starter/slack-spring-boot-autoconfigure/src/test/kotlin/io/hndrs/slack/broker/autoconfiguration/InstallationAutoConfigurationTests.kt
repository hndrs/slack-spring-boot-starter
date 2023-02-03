package io.hndrs.slack.broker.autoconfiguration

import io.hndrs.slack.broker.installation.InstallationBroker
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration

@DisplayName("Test Installation Auto configuration")
class InstallationAutoConfigurationTests {

    private val contextRunner = TestApplicationContext.base()
        .withSystemProperties(
            "slack.installation-endpoint.error-redirect-url:http://localhost:8080/installation/error",
            "slack.installation-endpoint.success-redirect-url:http://localhost:8080/installation/success"
        )
        .withConfiguration(
            AutoConfigurations.of(
                BaseAutoConfiguration::class.java,
                InstallationEndpointAutoConfiguration::class.java,
                TeamStoreAutoconfiguration::class.java,
                WebMvcAutoConfiguration::class.java
            )
        )

    @DisplayName("InstallationBroker Registration")
    @Test
    fun installationBrokerRegistration() {
        contextRunner.run {
            Assertions.assertDoesNotThrow { it.getBean(InstallationBroker::class.java) }
        }
    }
}
