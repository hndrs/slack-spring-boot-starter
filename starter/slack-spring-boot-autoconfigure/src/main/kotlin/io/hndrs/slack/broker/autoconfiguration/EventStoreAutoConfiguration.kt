package io.hndrs.slack.broker.autoconfiguration

import io.hndrs.slack.broker.store.event.EventStore
import io.hndrs.slack.broker.store.event.InMemoryEventStore
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class EventStoreAutoConfiguration {

    /**
     * Registers the [InMemoryEventStore] when no other [EventStore] is registered
     */
    @ConditionalOnMissingBean
    @Bean
    open fun eventStore(): EventStore {
        return InMemoryEventStore()
    }
}
