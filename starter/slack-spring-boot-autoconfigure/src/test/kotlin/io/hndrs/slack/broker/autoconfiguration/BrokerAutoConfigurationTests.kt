package io.hndrs.slack.broker.autoconfiguration

import io.hndrs.slack.broker.event.http.EventArgumentResolver
import io.hndrs.slack.broker.event.http.EventBroker
import io.hndrs.slack.broker.exception.SlackExceptionHandler
import io.hndrs.slack.broker.receiver.SL4JLoggingHandler
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.getBean
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@DisplayName("AutoConfiguration Tests")
class BrokerAutoConfigurationTests {

    @DisplayName("SlackArgumentResolver Registration")
    @Test
    fun slackArgumentResolverRegistrations() {
        TestApplicationContext.base()
            .withConfiguration(
                AutoConfigurations.of(
                    BaseAutoConfiguration::class.java,
                    SlackBrokerAutoConfiguration::class.java,
                    TeamStoreAutoconfiguration::class.java,
                    WebMvcAutoConfiguration::class.java
                )
            )
            .run {
                val listOf = ArrayList<HandlerMethodArgumentResolver>()
                val bean =
                    it.getBean<WebMvcConfigurer>(
                        "io.hndrs.slack.broker.autoconfiguration.SlackBrokerAutoConfiguration\$BrokerAutoConfiguration"
                    )

                bean.addArgumentResolvers(listOf)
                assertTrue(listOf[0] is EventArgumentResolver)
            }
    }

    @DisplayName("SL4JLoggingReceiver Registration")
    @Test
    fun testSL4JLoggingReceiverRegistration() {
        TestApplicationContext.base()
            .withConfiguration(
                AutoConfigurations.of(
                    BaseAutoConfiguration::class.java,
                )
            )
            .run {
                assertDoesNotThrow { it.getBean(SL4JLoggingHandler::class.java) }
            }

        ApplicationContextRunner()
            .withSystemProperties("slack.logging.enabled:false")
            .withConfiguration(
                AutoConfigurations.of(
                    BaseAutoConfiguration::class.java,
                )
            )
            .run {
                assertThrows<NoSuchBeanDefinitionException> { it.getBean(SL4JLoggingHandler::class.java) }
            }

        ApplicationContextRunner()
            .withSystemProperties("slack.logging.enabled:true")
            .withConfiguration(
                AutoConfigurations.of(
                    BaseAutoConfiguration::class.java,
                )
            )
            .run {
                assertDoesNotThrow { it.getBean(SL4JLoggingHandler::class.java) }
            }
    }

    @DisplayName("SlackExceptionHandler Registration")
    @Test
    fun slackExceptionHandlerRegistration() {
        TestApplicationContext.base()
            .withConfiguration(
                AutoConfigurations.of(
                    BaseAutoConfiguration::class.java,
                )
            )
            .run {
                assertDoesNotThrow { it.getBean(SlackExceptionHandler::class.java) }
            }
    }

    @DisplayName("EventBroker Registration")
    @Test
    fun eventBrokerRegistration() {
        TestApplicationContext.base()
            .withConfiguration(
                AutoConfigurations.of(
                    BaseAutoConfiguration::class.java,
                    SlackBrokerAutoConfiguration::class.java,
                    TeamStoreAutoconfiguration::class.java,
                    WebMvcAutoConfiguration::class.java
                )
            )
            .run {
                assertDoesNotThrow { it.getBean(EventBroker::class.java) }
            }
    }
}
