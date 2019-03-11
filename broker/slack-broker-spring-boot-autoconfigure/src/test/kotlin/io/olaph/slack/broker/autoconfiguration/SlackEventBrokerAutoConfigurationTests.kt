package io.olaph.slack.broker.autoconfiguration

import io.olaph.slack.broker.broker.CommandBroker
import io.olaph.slack.broker.broker.EventBroker
import io.olaph.slack.broker.broker.InstallationBroker
import io.olaph.slack.broker.configuration.SlackCommandArgumentResolver
import io.olaph.slack.broker.receiver.SL4JLoggingReceiver
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@DisplayName("AutoConfiguration Tests")
class SlackEventBrokerAutoConfigurationTests {

    private val contextRunner = ApplicationContextRunner()
            .withSystemProperties(
                    "slack.installation.error-redirect-url:http://localhost:8080/installation/error",
                    "slack.installation.success-redirect-url:http://localhost:8080/installation/success"
            )
            .withConfiguration(AutoConfigurations.of(SlackBrokerAutoConfiguration::class.java, WebMvcAutoConfiguration::class.java))

    @DisplayName("SlackArgumentResolver Registration")
    @Test
    fun slackCommandArgumentResolverRegistration() {
        contextRunner.run {
            val listOf = ArrayList<HandlerMethodArgumentResolver>()
            it.getBean(WebMvcConfigurer::class.java).addArgumentResolvers(listOf)
            assertTrue(listOf[0] is SlackCommandArgumentResolver)
        }
    }

    @DisplayName("SL4JLoggingBroker Registration")
    @Test
    fun testSL4JLoggingBrokerRegistration() {
        contextRunner.run {
            assertNotNull(it.getBean(SL4JLoggingReceiver::class.java))
        }
    }

    @DisplayName("CommandBroker Registration")
    @Test
    fun commandReceiverRegistration() {
        contextRunner.run {
            assertNotNull(it.getBean(CommandBroker::class.java))
        }
    }

    @DisplayName("EventBroker Registration")
    @Test
    fun eventReceiverRegistration() {
        contextRunner.run {
            assertNotNull(it.getBean(EventBroker::class.java))
        }
    }

    @DisplayName("InstallationBroker Registration")
    @Test
    fun installationBrokerRegistration() {
        contextRunner.run {
            assertNotNull(it.getBean(InstallationBroker::class.java))
        }
    }
}
