package io.hndrs.slack.broker.autoconfiguration

import io.hndrs.slack.broker.command.CommandBroker
import io.hndrs.slack.broker.event.http.EventBroker
import io.hndrs.slack.broker.installation.InstallationBroker
import io.hndrs.slack.broker.interactive.InteractiveComponentBroker
import io.hndrs.slack.broker.metrics.CommandMetricsCollector
import io.hndrs.slack.broker.metrics.EventMetricsCollector
import io.hndrs.slack.broker.metrics.InstallationMetrics
import io.hndrs.slack.broker.metrics.InteractiveComponentMetricsCollector
import io.micrometer.core.instrument.MeterRegistry
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.boot.test.context.FilteredClassLoader

@DisplayName("Test Installation Auto configuration")
class MetricsAutoConfigurationTests {


    @Nested
    @DisplayName("Installation Metrics AutoConfiguration")
    class InstallationMetricTests {

        @DisplayName("InstallationMetrics Registration")
        @Test
        fun installationMetricsRegistration() {
            TestApplicationContext.base()
                .withSystemProperties(
                    "slack.installation.error-redirect-url:http://localhost:8080/installation/error",
                    "slack.installation.success-redirect-url:http://localhost:8080/installation/success"
                )
                .withConfiguration(
                    AutoConfigurations.of(
                        SlackBrokerAutoConfiguration::class.java,
                        TeamStoreAutoconfiguration::class.java,
                        WebMvcAutoConfiguration::class.java
                    )
                )
                .run {
                    Assertions.assertDoesNotThrow { it.getBean(InstallationMetrics::class.java) }
                }
        }

        @DisplayName("InstallationBroker Registration Without Micrometer")
        @Test
        fun installationMetricsRegistrationWithoutMicrometer() {
            TestApplicationContext.base()
                .withSystemProperties(
                    "slack.installation.error-redirect-url:http://localhost:8080/installation/error",
                    "slack.installation.success-redirect-url:http://localhost:8080/installation/success"
                )
                .withClassLoader(FilteredClassLoader(MeterRegistry::class.java))
                .withConfiguration(
                    AutoConfigurations.of(
                        SlackBrokerAutoConfiguration::class.java,
                        TeamStoreAutoconfiguration::class.java,
                        WebMvcAutoConfiguration::class.java
                    )
                )
                .run {
                    Assertions.assertThrows(NoSuchBeanDefinitionException::class.java) { it.getBean(InstallationMetrics::class.java) }
                    Assertions.assertDoesNotThrow { it.getBean(InstallationBroker::class.java) }
                }

        }
    }

    @Nested
    @DisplayName("Event Metrics AutoConfiguration")
    class EventMetricsTests {

        @DisplayName("EventMetrics Registration")
        @Test
        fun eventMetricsRegistration() {
            TestApplicationContext.base()
                .withSystemProperties(
                    "slack.installation.error-redirect-url:http://localhost:8080/installation/error",
                    "slack.installation.success-redirect-url:http://localhost:8080/installation/success"
                )
                .withConfiguration(
                    AutoConfigurations.of(
                        SlackBrokerAutoConfiguration::class.java,
                        TeamStoreAutoconfiguration::class.java,
                        WebMvcAutoConfiguration::class.java
                    )
                )
                .run {
                    Assertions.assertDoesNotThrow { it.getBean(EventMetricsCollector::class.java) }
                }
        }

        @DisplayName("EventBroker Registration Without Micrometer")
        @Test
        fun eventMetricsRegistrationWithoutMicrometer() {
            TestApplicationContext.base()
                .withSystemProperties(
                    "slack.installation.error-redirect-url:http://localhost:8080/installation/error",
                    "slack.installation.success-redirect-url:http://localhost:8080/installation/success"
                )
                .withClassLoader(FilteredClassLoader(MeterRegistry::class.java))
                .withConfiguration(
                    AutoConfigurations.of(
                        SlackBrokerAutoConfiguration::class.java,
                        TeamStoreAutoconfiguration::class.java,
                        WebMvcAutoConfiguration::class.java
                    )
                )
                .run {
                    Assertions.assertThrows(NoSuchBeanDefinitionException::class.java) {
                        it.getBean(
                            EventMetricsCollector::class.java
                        )
                    }
                    Assertions.assertDoesNotThrow { it.getBean(EventBroker::class.java) }
                }

        }
    }

    @Nested
    @DisplayName("Command Metrics AutoConfiguration")
    class CommandMetricsTests {

        @DisplayName("CommandMetrics Registration")
        @Test
        fun commandMetricsRegistration() {
            TestApplicationContext.base()
                .withSystemProperties(
                    "slack.installation.error-redirect-url:http://localhost:8080/installation/error",
                    "slack.installation.success-redirect-url:http://localhost:8080/installation/success"
                )
                .withConfiguration(
                    AutoConfigurations.of(
                        SlackBrokerAutoConfiguration::class.java,
                        TeamStoreAutoconfiguration::class.java,
                        WebMvcAutoConfiguration::class.java
                    )
                )
                .run {
                    Assertions.assertDoesNotThrow { it.getBean(CommandMetricsCollector::class.java) }
                }
        }

        @DisplayName("CommandBroker Registration Without Micrometer")
        @Test
        fun commandMetricsRegistrationWithoutMicrometer() {
            TestApplicationContext.base()
                .withSystemProperties(
                    "slack.installation.error-redirect-url:http://localhost:8080/installation/error",
                    "slack.installation.success-redirect-url:http://localhost:8080/installation/success"
                )
                .withClassLoader(FilteredClassLoader(MeterRegistry::class.java))
                .withConfiguration(
                    AutoConfigurations.of(
                        SlackBrokerAutoConfiguration::class.java,
                        TeamStoreAutoconfiguration::class.java,
                        WebMvcAutoConfiguration::class.java
                    )
                )
                .run {
                    Assertions.assertThrows(NoSuchBeanDefinitionException::class.java) {
                        it.getBean(
                            CommandMetricsCollector::class.java
                        )
                    }
                    Assertions.assertDoesNotThrow { it.getBean(CommandBroker::class.java) }
                }

        }
    }

    @Nested
    @DisplayName("Command Metrics AutoConfiguration")
    class InteractiveComponentMetricsTests {

        @DisplayName("CommandMetrics Registration")
        @Test
        fun interactiveComponentMetricsRegistration() {
            TestApplicationContext.base()
                .withSystemProperties(
                    "slack.installation.error-redirect-url:http://localhost:8080/installation/error",
                    "slack.installation.success-redirect-url:http://localhost:8080/installation/success"
                )
                .withConfiguration(
                    AutoConfigurations.of(
                        SlackBrokerAutoConfiguration::class.java,
                        TeamStoreAutoconfiguration::class.java,
                        WebMvcAutoConfiguration::class.java
                    )
                )
                .run {
                    Assertions.assertDoesNotThrow { it.getBean(CommandMetricsCollector::class.java) }
                }
        }

        @DisplayName("CommandBroker Registration Without Micrometer")
        @Test
        fun interactiveComponentMetricsRegistrationWithoutMicrometer() {
            TestApplicationContext.base()
                .withSystemProperties(
                    "slack.installation.error-redirect-url:http://localhost:8080/installation/error",
                    "slack.installation.success-redirect-url:http://localhost:8080/installation/success"
                )
                .withClassLoader(FilteredClassLoader(MeterRegistry::class.java))
                .withConfiguration(
                    AutoConfigurations.of(
                        SlackBrokerAutoConfiguration::class.java,
                        TeamStoreAutoconfiguration::class.java,
                        WebMvcAutoConfiguration::class.java
                    )
                )
                .run {
                    Assertions.assertThrows(NoSuchBeanDefinitionException::class.java) {
                        it.getBean(
                            InteractiveComponentMetricsCollector::class.java
                        )
                    }
                    Assertions.assertDoesNotThrow { it.getBean(InteractiveComponentBroker::class.java) }
                }

        }
    }
}
