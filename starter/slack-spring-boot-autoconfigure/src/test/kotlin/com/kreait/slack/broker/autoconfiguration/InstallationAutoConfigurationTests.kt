package com.kreait.slack.broker.autoconfiguration

import com.kreait.slack.broker.broker.InstallationBroker
import com.kreait.slack.broker.metrics.EventMetricsCollector
import io.micrometer.core.instrument.MeterRegistry
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.boot.test.context.FilteredClassLoader
import org.springframework.boot.test.context.runner.WebApplicationContextRunner

@DisplayName("Test Installation Auto configuration")
class InstallationAutoConfigurationTests {

    private fun applicationContext(): WebApplicationContextRunner =
            TestApplicationContext.base()
                    .withSystemProperties(
                            "slack.installation.error-redirect-url:http://localhost:8080/installation/error",
                            "slack.installation.success-redirect-url:http://localhost:8080/installation/success"
                    )
                    .withConfiguration(AutoConfigurations.of(
                            SlackBrokerAutoConfiguration::class.java,
                            TeamStoreAutoconfiguration::class.java,
                            WebMvcAutoConfiguration::class.java))


    @DisplayName("InstallationBroker Registration")
    @Test
    fun installationBrokerRegistration() {
        applicationContext()
                .withConfiguration(AutoConfigurations.of(InstallationAutoConfiguration::class.java))
                .run {
                    Assertions.assertDoesNotThrow { it.getBean(InstallationBroker::class.java) }
                }
    }

    @Nested
    @DisplayName("InstallationMetrics")
    internal inner class EventMetricsTests {

        @DisplayName("InstallationMetrics Registration")
        @Test
        fun eventMetricsRegistration() {
            applicationContext()
                    .withConfiguration(AutoConfigurations.of(
                            InstallationAutoConfiguration::class.java))
                    .run {
                        Assertions.assertDoesNotThrow { it.getBean(EventMetricsCollector::class.java) }
                    }
        }

        @DisplayName("InstallationMetrics Registration Without Micrometer")
        @Test
        fun eventMetricsRegistrationWithoutMicrometer() {
            applicationContext()
                    .withConfiguration(AutoConfigurations.of(
                            InstallationAutoConfiguration::class.java))
                    .withClassLoader(FilteredClassLoader(MeterRegistry::class.java))
                    .run {
                        Assertions.assertThrows(NoSuchBeanDefinitionException::class.java) { it.getBean(EventMetricsCollector::class.java) }
                    }

        }
    }
}
