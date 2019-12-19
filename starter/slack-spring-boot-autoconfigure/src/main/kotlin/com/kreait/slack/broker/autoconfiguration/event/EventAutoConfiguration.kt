package com.kreait.slack.broker.autoconfiguration.event

import com.kreait.slack.broker.autoconfiguration.SlackBrokerAutoConfiguration
import com.kreait.slack.broker.autoconfiguration.SlackBrokerConfigurationProperties
import com.kreait.slack.broker.autoconfiguration.credentials.CredentialsProvider
import com.kreait.slack.broker.broker.EventBroker
import com.kreait.slack.broker.configuration.EventArgumentResolver
import com.kreait.slack.broker.metrics.EventMetrics
import com.kreait.slack.broker.metrics.EventMetricsCollector
import com.kreait.slack.broker.receiver.EventReceiver
import com.kreait.slack.broker.store.event.EventStore
import com.kreait.slack.broker.store.event.InMemoryEventStore
import com.kreait.slack.broker.store.team.TeamStore
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.boot.autoconfigure.AutoConfigureBefore
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@EnableConfigurationProperties(SlackBrokerConfigurationProperties::class)
@AutoConfigureAfter(SlackBrokerAutoConfiguration::class)
@Configuration
open class EventAutoConfiguration(private val prop: SlackBrokerConfigurationProperties) : WebMvcConfigurer {

    /**
     * Registers the [InMemoryEventStore] when no other [EventStore] is registered
     */
    @ConditionalOnMissingBean
    @Bean
    open fun eventStore(): EventStore {
        return InMemoryEventStore()
    }

    @Configuration
    open class Broker(private val credentialsProvider: CredentialsProvider) : WebMvcConfigurer {

        /**
         * Registers the [EventBroker] which forwards events to all [EventReceiver]s
         */
        @Bean
        open fun eventBroker(slackEventReceivers: List<EventReceiver>, teamStore: TeamStore, eventStore: EventStore, metricsCollector: EventMetricsCollector?): EventBroker {
            return EventBroker(slackEventReceivers, teamStore, eventStore, metricsCollector)
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

    @AutoConfigureBefore(Broker::class)
    @ConditionalOnClass(MeterRegistry::class)
    @Configuration
    open class Metrics {

        /**
         * MeterBinder that tracks event metrics
         */
        @ConditionalOnMissingBean
        @Bean
        open fun eventMetrics(): EventMetrics {
            return EventMetrics()
        }
    }
}
