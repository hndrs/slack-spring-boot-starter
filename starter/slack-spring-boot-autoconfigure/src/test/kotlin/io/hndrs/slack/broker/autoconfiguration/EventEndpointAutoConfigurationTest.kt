package io.hndrs.slack.broker.autoconfiguration

import io.hndrs.slack.broker.event.http.EventArgumentResolver
import io.hndrs.slack.broker.event.http.EventEndpoint
import io.hndrs.slack.broker.store.event.InMemoryEventStore
import io.hndrs.slack.broker.store.team.InMemoryTeamStore
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.getBean
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.WebApplicationContextRunner
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

class EventEndpointAutoConfigurationTest {

    @Test
    fun `event endpoint registered`() {
        WebApplicationContextRunner()
            .withBean(InMemoryTeamStore::class.java)
            .withBean(InMemoryEventStore::class.java)
            .withConfiguration(
                AutoConfigurations.of(
                    BaseAutoConfiguration::class.java,
                    EventEndpointAutoConfiguration::class.java
                )
            )
            .run {
                shouldNotThrow<Exception> { it.getBean(EventEndpoint::class.java) }
            }
    }

    @Test
    fun `required argument resolver is registered`() {
        WebApplicationContextRunner()
            .withBean(InMemoryTeamStore::class.java)
            .withBean(InMemoryEventStore::class.java)
            .withConfiguration(
                AutoConfigurations.of(
                    BaseAutoConfiguration::class.java,
                    EventEndpointAutoConfiguration::class.java
                )
            )
            .run {
                val resolvers = mutableListOf<HandlerMethodArgumentResolver>()
                it.getBean<WebMvcConfigurer>()
                    .addArgumentResolvers(resolvers)

                resolvers[0] shouldBe instanceOf<EventArgumentResolver>()
            }
    }
}
