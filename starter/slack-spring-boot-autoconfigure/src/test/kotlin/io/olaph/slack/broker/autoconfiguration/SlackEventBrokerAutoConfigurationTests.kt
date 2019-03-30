package io.olaph.slack.broker.autoconfiguration

import io.olaph.slack.broker.broker.CommandBroker
import io.olaph.slack.broker.broker.EventBroker
import io.olaph.slack.broker.broker.InstallationBroker
import io.olaph.slack.broker.configuration.InteractiveResponseArgumentResolver
import io.olaph.slack.broker.configuration.SlackCommandArgumentResolver
import io.olaph.slack.broker.receiver.SL4JLoggingReceiver
import io.olaph.slack.broker.security.VerificationHandlerInterceptor
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
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
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
            val bean = it.getBean<WebMvcConfigurer>("io.olaph.slack.broker.autoconfiguration.SlackBrokerAutoConfiguration\$BrokerConfiguration")

            bean.addArgumentResolvers(listOf)
            assertTrue(listOf[0] is SlackCommandArgumentResolver)
            assertTrue(listOf[1] is InteractiveResponseArgumentResolver)
        }
    }

    @DisplayName("SlackArgumentResolver Registration")
    @Test
    fun verificationInterceptorRegistration() {
        ApplicationContextRunner()
                .withSystemProperties("slack.security.signing-secret:mysecret")
                .withConfiguration(AutoConfigurations.of(SlackBrokerAutoConfiguration::class.java, WebMvcAutoConfiguration::class.java))
                .run {
                    val registry = object : InterceptorRegistry() {
                        public override fun getInterceptors(): MutableList<Any> {
                            return super.getInterceptors()
                        }
                    }
                    val bean = it.getBean<WebMvcConfigurer>("io.olaph.slack.broker.autoconfiguration.SlackBrokerAutoConfiguration\$SecurityConfiguration")

                    bean.addInterceptors(registry)
                    assertTrue(registry.interceptors[0] is VerificationHandlerInterceptor)
                }

        ApplicationContextRunner()
                .withSystemProperties("slack.security.signing-secret:")
                .withConfiguration(AutoConfigurations.of(SlackBrokerAutoConfiguration::class.java, WebMvcAutoConfiguration::class.java))
                .run {
                    assertThrows<NoSuchBeanDefinitionException> { it.getBean<WebMvcConfigurer>("io.olaph.slack.broker.autoconfiguration.SlackBrokerAutoConfiguration\$SecurityConfiguration") }
                }
    }

    @DisplayName("SL4JLoggingBroker Registration")
    @Test
    fun testSL4JLoggingBrokerRegistration() {
        contextRunner.run {
            assertDoesNotThrow { it.getBean(SL4JLoggingReceiver::class.java) }
        }

        ApplicationContextRunner()
                .withSystemProperties("slack.logging.enabled:false")
                .withConfiguration(AutoConfigurations.of(SlackBrokerAutoConfiguration::class.java, WebMvcAutoConfiguration::class.java))
                .run {
                    assertThrows<NoSuchBeanDefinitionException> { it.getBean(SL4JLoggingReceiver::class.java) }
                }

        ApplicationContextRunner()
                .withSystemProperties("slack.logging.enabled:true")
                .withConfiguration(AutoConfigurations.of(SlackBrokerAutoConfiguration::class.java, WebMvcAutoConfiguration::class.java))
                .run {
                    assertDoesNotThrow { it.getBean(SL4JLoggingReceiver::class.java) }
                }
    }


    @DisplayName("CommandBroker Registration")
    @Test
    fun commandReceiverRegistration() {
        contextRunner.run {
            assertDoesNotThrow { it.getBean(CommandBroker::class.java) }
        }
    }

    @DisplayName("EventBroker Registration")
    @Test
    fun eventReceiverRegistration() {
        contextRunner.run {
            assertDoesNotThrow { it.getBean(EventBroker::class.java) }
        }
    }

    @DisplayName("InstallationBroker Registration")
    @Test
    fun installationBrokerRegistration() {
        contextRunner.run {
            assertDoesNotThrow { it.getBean(InstallationBroker::class.java) }
        }
    }
}
