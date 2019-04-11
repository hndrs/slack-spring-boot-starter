package io.olaph.slack.broker.autoconfiguration

import io.micrometer.core.instrument.MeterRegistry
import io.olaph.slack.broker.broker.CommandBroker
import io.olaph.slack.broker.broker.EventBroker
import io.olaph.slack.broker.broker.InstallationBroker
import io.olaph.slack.broker.broker.InteractiveComponentBroker
import io.olaph.slack.broker.metrics.CommandMetricsCollector
import io.olaph.slack.broker.metrics.EventMetricsCollector
import io.olaph.slack.broker.metrics.InstallationMetrics
import io.olaph.slack.broker.metrics.InteractiveComponentMetricsCollector
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.boot.test.context.FilteredClassLoader
import org.springframework.boot.test.context.runner.ApplicationContextRunner

@DisplayName("Test Installation Auto configuration")
class MetricsAutoConfigurationTests {


    @Nested
    @DisplayName("Installation Metrics AutoConfiguration")
    class InstallationMetricTests {

        @DisplayName("InstallationMetrics Registration")
        @Test
        fun installationMetricsRegistration() {
            ApplicationContextRunner()
                    .withSystemProperties(
                            "slack.installation.error-redirect-url:http://localhost:8080/installation/error",
                            "slack.installation.success-redirect-url:http://localhost:8080/installation/success",
                            "slack.installation.client-id:123",
                            "slack.installation.client-secret:1234"
                    )
                    .withConfiguration(AutoConfigurations.of(SlackBrokerAutoConfiguration::class.java, WebMvcAutoConfiguration::class.java))
                    .run {
                        Assertions.assertDoesNotThrow { it.getBean(InstallationMetrics::class.java) }
                    }
        }

        @DisplayName("InstallationBroker Registration Without Micrometer")
        @Test
        fun installationMetricsRegistrationWithoutMicrometer() {
            ApplicationContextRunner()
                    .withSystemProperties(
                            "slack.installation.error-redirect-url:http://localhost:8080/installation/error",
                            "slack.installation.success-redirect-url:http://localhost:8080/installation/success",
                            "slack.installation.client-id:123",
                            "slack.installation.client-secret:1234"
                    )
                    .withClassLoader(FilteredClassLoader(MeterRegistry::class.java))
                    .withConfiguration(AutoConfigurations.of(SlackBrokerAutoConfiguration::class.java, WebMvcAutoConfiguration::class.java))
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
            ApplicationContextRunner()
                    .withSystemProperties(
                            "slack.installation.error-redirect-url:http://localhost:8080/installation/error",
                            "slack.installation.success-redirect-url:http://localhost:8080/installation/success",
                            "slack.installation.client-id:123",
                            "slack.installation.client-secret:1234"
                    )
                    .withConfiguration(AutoConfigurations.of(SlackBrokerAutoConfiguration::class.java, WebMvcAutoConfiguration::class.java))
                    .run {
                        Assertions.assertDoesNotThrow { it.getBean(EventMetricsCollector::class.java) }
                    }
        }

        @DisplayName("EventBroker Registration Without Micrometer")
        @Test
        fun eventMetricsRegistrationWithoutMicrometer() {
            ApplicationContextRunner()
                    .withSystemProperties(
                            "slack.installation.error-redirect-url:http://localhost:8080/installation/error",
                            "slack.installation.success-redirect-url:http://localhost:8080/installation/success",
                            "slack.installation.client-id:123",
                            "slack.installation.client-secret:1234"
                    )
                    .withClassLoader(FilteredClassLoader(MeterRegistry::class.java))
                    .withConfiguration(AutoConfigurations.of(SlackBrokerAutoConfiguration::class.java, WebMvcAutoConfiguration::class.java))
                    .run {
                        Assertions.assertThrows(NoSuchBeanDefinitionException::class.java) { it.getBean(EventMetricsCollector::class.java) }
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
            ApplicationContextRunner()
                    .withSystemProperties(
                            "slack.installation.error-redirect-url:http://localhost:8080/installation/error",
                            "slack.installation.success-redirect-url:http://localhost:8080/installation/success",
                            "slack.installation.client-id:123",
                            "slack.installation.client-secret:1234"
                    )
                    .withConfiguration(AutoConfigurations.of(SlackBrokerAutoConfiguration::class.java, WebMvcAutoConfiguration::class.java))
                    .run {
                        Assertions.assertDoesNotThrow { it.getBean(CommandMetricsCollector::class.java) }
                    }
        }

        @DisplayName("CommandBroker Registration Without Micrometer")
        @Test
        fun commandMetricsRegistrationWithoutMicrometer() {
            ApplicationContextRunner()
                    .withSystemProperties(
                            "slack.installation.error-redirect-url:http://localhost:8080/installation/error",
                            "slack.installation.success-redirect-url:http://localhost:8080/installation/success",
                            "slack.installation.client-id:123",
                            "slack.installation.client-secret:1234"
                    )
                    .withClassLoader(FilteredClassLoader(MeterRegistry::class.java))
                    .withConfiguration(AutoConfigurations.of(SlackBrokerAutoConfiguration::class.java, WebMvcAutoConfiguration::class.java))
                    .run {
                        Assertions.assertThrows(NoSuchBeanDefinitionException::class.java) { it.getBean(CommandMetricsCollector::class.java) }
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
            ApplicationContextRunner()
                    .withSystemProperties(
                            "slack.installation.error-redirect-url:http://localhost:8080/installation/error",
                            "slack.installation.success-redirect-url:http://localhost:8080/installation/success",
                            "slack.installation.client-id:123",
                            "slack.installation.client-secret:1234"
                    )
                    .withConfiguration(AutoConfigurations.of(SlackBrokerAutoConfiguration::class.java, WebMvcAutoConfiguration::class.java))
                    .run {
                        Assertions.assertDoesNotThrow { it.getBean(CommandMetricsCollector::class.java) }
                    }
        }

        @DisplayName("CommandBroker Registration Without Micrometer")
        @Test
        fun interactiveComponentMetricsRegistrationWithoutMicrometer() {
            ApplicationContextRunner()
                    .withSystemProperties(
                            "slack.installation.error-redirect-url:http://localhost:8080/installation/error",
                            "slack.installation.success-redirect-url:http://localhost:8080/installation/success",
                            "slack.installation.client-id:123",
                            "slack.installation.client-secret:1234"
                    )
                    .withClassLoader(FilteredClassLoader(MeterRegistry::class.java))
                    .withConfiguration(AutoConfigurations.of(SlackBrokerAutoConfiguration::class.java, WebMvcAutoConfiguration::class.java))
                    .run {
                        Assertions.assertThrows(NoSuchBeanDefinitionException::class.java) { it.getBean(InteractiveComponentMetricsCollector::class.java) }
                        Assertions.assertDoesNotThrow { it.getBean(InteractiveComponentBroker::class.java) }
                    }

        }
    }
}
