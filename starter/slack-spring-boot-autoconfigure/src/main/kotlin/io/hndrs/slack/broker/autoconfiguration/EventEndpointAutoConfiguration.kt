package io.hndrs.slack.broker.autoconfiguration

import io.hndrs.slack.broker.autoconfiguration.credentials.CredentialsProvider
import io.hndrs.slack.broker.event.EventHandler
import io.hndrs.slack.broker.event.http.EventArgumentResolver
import io.hndrs.slack.broker.event.http.EventEndpoint
import io.hndrs.slack.broker.store.event.EventStore
import io.hndrs.slack.broker.store.team.TeamStore
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
open class EventEndpointAutoConfiguration(
    private val credentialsProvider: CredentialsProvider,
) : WebMvcConfigurer {

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(EventArgumentResolver(credentialsProvider.applicationCredentials().signingSecret))
    }

    /**
     * Registers the [EventEndpoint] which forwards events to all [EventHandler]s
     */
    @Bean
    open fun eventEndpoint(
        eventHandlers: List<EventHandler>,
        teamStore: TeamStore,
        eventStore: EventStore,
    ): EventEndpoint {
        return EventEndpoint(eventHandlers, teamStore, eventStore)
    }
}
