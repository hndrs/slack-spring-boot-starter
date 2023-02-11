package io.hndrs.slack.broker.autoconfiguration

import io.hndrs.slack.broker.command.CommandEndpoint
import io.hndrs.slack.broker.command.DefaultUnknownCommandHandler
import io.hndrs.slack.broker.command.SlashCommandArgumentResolver
import io.hndrs.slack.broker.store.team.InMemoryTeamStore
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.getBean
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.WebApplicationContextRunner
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

class CommandEndpointAutoConfigurationTest {

    @Test
    fun `command endpoint registered`() {
        WebApplicationContextRunner()
            .withBean(InMemoryTeamStore::class.java)
            .withConfiguration(
                AutoConfigurations.of(
                    BaseAutoConfiguration::class.java,
                    CommandEndpointAutoConfiguration::class.java
                )
            )
            .run {
                shouldNotThrow<Exception> { it.getBean(CommandEndpoint::class.java) }
            }
    }

    @Test
    fun `default unknown command handler enabled`() {
        WebApplicationContextRunner()
            .withBean(InMemoryTeamStore::class.java)
            .withConfiguration(
                AutoConfigurations.of(
                    BaseAutoConfiguration::class.java,
                    CommandEndpointAutoConfiguration::class.java
                )
            )
            .run {
                shouldNotThrow<Exception> { it.getBean(DefaultUnknownCommandHandler::class.java) }
            }
    }

    @Test
    fun `default unknown command handler disabled`() {
        WebApplicationContextRunner()
            .withBean(InMemoryTeamStore::class.java)
            .withConfiguration(
                AutoConfigurations.of(
                    BaseAutoConfiguration::class.java,
                    CommandEndpointAutoConfiguration::class.java
                )
            )
            .withPropertyValues(
                "${CommandEndpointConfigurationProperties.UNKNOWN_COMMAND_PREFIX}:false"
            )
            .run {
                shouldThrow<Exception> { it.getBean(DefaultUnknownCommandHandler::class.java) }
            }
    }

    @Test
    fun `required argument resolver is registered`() {
        WebApplicationContextRunner()
            .withBean(InMemoryTeamStore::class.java)
            .withConfiguration(
                AutoConfigurations.of(
                    BaseAutoConfiguration::class.java,
                    CommandEndpointAutoConfiguration::class.java
                )
            )
            .run {
                val resolvers = mutableListOf<HandlerMethodArgumentResolver>()
                it.getBean<WebMvcConfigurer>()
                    .addArgumentResolvers(resolvers)

                resolvers[0] shouldBe instanceOf<SlashCommandArgumentResolver>()
            }
    }
}
