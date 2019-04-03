package io.olaph.slack.broker.autoconfiguration

import io.olaph.slack.broker.broker.CommandBroker
import io.olaph.slack.broker.broker.EventBroker
import io.olaph.slack.broker.configuration.EventRequestArgumentResolver
import io.olaph.slack.broker.configuration.InteractiveResponseArgumentResolver
import io.olaph.slack.broker.configuration.SlackCommandArgumentResolver
import io.olaph.slack.broker.exception.SlackExceptionHandler
import io.olaph.slack.broker.receiver.SL4JLoggingReceiver
import io.olaph.slack.broker.store.InMemoryTeamStore
import io.olaph.slack.broker.store.Team
import io.olaph.slack.broker.store.TeamStore
import io.olaph.slack.client.SlackClient
import io.olaph.slack.client.spring.DefaultSlackClient
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
import org.springframework.boot.test.context.runner.WebApplicationContextRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@DisplayName("AutoConfiguration Tests")
class BrokerAutoConfigurationTests {

    private val contextRunner = ApplicationContextRunner()
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
            assertTrue(listOf[2] is EventRequestArgumentResolver)
        }
    }


    @DisplayName("SL4JLoggingReceiver Registration")
    @Test
    fun testSL4JLoggingReceiverRegistration() {
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

    @DisplayName("InMemoryTeamStore Registration")
    @Test
    fun teamStoreRegistration() {
        contextRunner.run {
            assertDoesNotThrow { it.getBean(TeamStore::class.java) }
            assertTrue(it.getBean(TeamStore::class.java) is InMemoryTeamStore)
        }
    }

    @DisplayName("SlackExceptionHandler Registration")
    @Test
    fun slackExceptionHandlerRegistration() {
        WebApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(SlackBrokerAutoConfiguration::class.java, WebMvcAutoConfiguration::class.java))
                .run {
                    assertDoesNotThrow { it.getBean(SlackExceptionHandler::class.java) }
                }
    }

    @DisplayName("SlackClient Registration")
    @Test
    fun slackApiClientRegistration() {
        ApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(SlackBrokerAutoConfiguration::class.java, WebMvcAutoConfiguration::class.java))
                .run {
                    assertDoesNotThrow { it.getBean(SlackClient::class.java) }
                    assertTrue(it.getBean(SlackClient::class.java) is DefaultSlackClient)
                }
    }

    @DisplayName("Custom TeamStore Registration")
    @Test
    fun teamCustomStoreRegistration() {
        val context = ApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(SlackBrokerAutoConfiguration::class.java, WebMvcAutoConfiguration::class.java, TestTeamStoreConfiguration::class.java))

        context.run {
            assertDoesNotThrow { it.getBean(TeamStore::class.java) }
            assertTrue(it.getBean(TeamStore::class.java) is TestTeamStoreConfiguration.TestTeamStore)
        }
    }

    @Configuration
    open class TestTeamStoreConfiguration {

        @Bean
        open fun testTeamStore(): TeamStore {
            return TestTeamStore()
        }

        class TestTeamStore : TeamStore {

            override fun findById(id: String): Team = throw NotImplementedError()

            override fun put(team: Team) {}

            override fun removeById(id: String) {}
        }
    }

    @DisplayName("EventBroker Registration")
    @Test
    fun eventReceiverRegistration() {
        contextRunner.run {
            assertDoesNotThrow { it.getBean(EventBroker::class.java) }
        }
    }
}
