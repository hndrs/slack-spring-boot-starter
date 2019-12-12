package com.kreait.slack.broker.autoconfiguration

import com.kreait.slack.api.contract.jackson.event.EventRequest
import com.kreait.slack.broker.store.event.EventStore
import com.kreait.slack.broker.store.event.InMemoryEventStore
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class EventStoreAutoConfigurationTests {

    @DisplayName("Custom EventStore Registration")
    @Test
    fun customEventStoreRegistration() {
        TestApplicationContext.base()
                .withConfiguration(AutoConfigurations.of(SlackBrokerAutoConfiguration::class.java, TeamStoreAutoconfiguration::class.java, WebMvcAutoConfiguration::class.java))
                .withUserConfiguration(TestConfiguration::class.java)
                .run {
                    Assertions.assertDoesNotThrow { it.getBean(EventStore::class.java) }
                    Assertions.assertTrue(it.getBean(EventStore::class.java) is TestEventStore)
                }
    }

    @DisplayName("InMemoryEventStore Registration")
    @Test
    fun eventStoreRegistration() {
        TestApplicationContext.base()
                .withConfiguration(AutoConfigurations.of(SlackBrokerAutoConfiguration::class.java, TeamStoreAutoconfiguration::class.java, WebMvcAutoConfiguration::class.java))
                .run {
                    Assertions.assertDoesNotThrow { it.getBean(EventStore::class.java) }
                    Assertions.assertTrue(it.getBean(EventStore::class.java) is InMemoryEventStore)
                }
    }

    @Configuration
    open class TestConfiguration {

        @Bean
        open fun testEventStore(): EventStore = TestEventStore()
    }

    class TestEventStore : EventStore {
        override fun exists(id: String): Boolean = false

        override fun put(event: EventRequest) {}

        override fun findAll(): List<EventRequest> = listOf()
    }
}
