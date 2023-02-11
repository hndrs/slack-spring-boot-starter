package io.hndrs.slack.broker.autoconfiguration

import io.hndrs.slack.broker.event.EventRequest
import io.hndrs.slack.broker.store.event.EventStore
import io.hndrs.slack.broker.store.event.InMemoryEventStore
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class EventStoreAutoConfigurationTest {

    @Test
    fun `default EventStore registered`() {
        ApplicationContextRunner()
            .withConfiguration(
                AutoConfigurations.of(EventStoreAutoConfiguration::class.java)
            ).run {
                shouldNotThrow<Exception> { it.getBean(EventStore::class.java) }
                it.getBean(EventStore::class.java) shouldBe instanceOf<InMemoryEventStore>()
            }
    }

    @Test
    fun `custom EventStore registered`() {
        ApplicationContextRunner()
            .withConfiguration(
                AutoConfigurations.of(EventStoreAutoConfiguration::class.java)
            )
            .withUserConfiguration(EventStoreConfiguration::class.java)
            .run {
                shouldNotThrow<Exception> { it.getBean(EventStore::class.java) }
                it.getBean(EventStore::class.java) shouldBe instanceOf<CustomEventStore>()
            }
    }

    @Configuration
    internal open class EventStoreConfiguration {

        @Bean
        open fun customEventStore() = CustomEventStore()
    }

    internal class CustomEventStore : EventStore {
        override fun exists(id: String): Boolean {
            // stub for test
            return false
        }

        override fun put(event: EventRequest) {
            // stub for test
        }
    }
}
