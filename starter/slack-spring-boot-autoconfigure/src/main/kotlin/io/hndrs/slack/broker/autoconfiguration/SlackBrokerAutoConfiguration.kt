package io.hndrs.slack.broker.autoconfiguration

import io.hndrs.slack.broker.autoconfiguration.credentials.CredentialsProvider
import io.hndrs.slack.broker.event.EventReceiver
import io.hndrs.slack.broker.event.http.EventArgumentResolver
import io.hndrs.slack.broker.event.http.EventBroker
import io.hndrs.slack.broker.store.event.EventStore
import io.hndrs.slack.broker.store.event.InMemoryEventStore
import io.hndrs.slack.broker.store.team.TeamStore
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * AutoConfiguration for SlackBroker
 *
 * @property configuration The configuration properties with which you can customise the auto-configuration
 */
@EnableConfigurationProperties(SlackBrokerConfigurationProperties::class)
@Configuration
open class SlackBrokerAutoConfiguration(private val configuration: SlackBrokerConfigurationProperties) {

    /**
     * Configuration that registers the Broker-Components
     *
     * @property configuration The Configuration properties with which you can customise the auto-configuration
     * @property credentialsProvider CredentialsProvider-chain that retrieves the credentials
     */
    //TODO rework
    @Configuration
    open class BrokerAutoConfiguration(
        private val configuration: SlackBrokerConfigurationProperties,
        private val credentialsProvider: CredentialsProvider,
    ) : WebMvcConfigurer {

        /**
         * Registers the [InMemoryEventStore] when no other [EventStore] is registered
         */
        @ConditionalOnMissingBean
        @Bean
        open fun eventStore(): EventStore {
            return InMemoryEventStore()
        }

        /**
         * Registers the [EventBroker] which forwards events to all [EventReceiver]s
         */
        @Bean
        open fun eventBroker(
            slackEventReceivers: List<EventReceiver>,
            teamStore: TeamStore,
            eventStore: EventStore,
        ): EventBroker {
            return EventBroker(slackEventReceivers, teamStore, eventStore)
        }

        /**
         * Adds argument-resolver to resolve incoming requests
         *
         * @param resolvers
         */
        override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
            val signingSecret = credentialsProvider.applicationCredentials().signingSecret
            resolvers.add(EventArgumentResolver(signingSecret))
        }
    }
}
