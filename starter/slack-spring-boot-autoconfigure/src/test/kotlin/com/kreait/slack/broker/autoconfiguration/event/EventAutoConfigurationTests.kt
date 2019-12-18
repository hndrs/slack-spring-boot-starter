package com.kreait.slack.broker.autoconfiguration.event

import com.kreait.slack.api.contract.jackson.event.EventRequest
import com.kreait.slack.broker.autoconfiguration.SlackBrokerAutoConfiguration
import com.kreait.slack.broker.autoconfiguration.TeamStoreAutoconfiguration
import com.kreait.slack.broker.autoconfiguration.TestApplicationContext
import com.kreait.slack.broker.broker.EventBroker
import com.kreait.slack.broker.configuration.EventArgumentResolver
import com.kreait.slack.broker.metrics.EventMetricsCollector
import com.kreait.slack.broker.store.event.EventStore
import com.kreait.slack.broker.store.event.InMemoryEventStore
import io.micrometer.core.instrument.MeterRegistry
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.getBean
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.boot.test.context.FilteredClassLoader
import org.springframework.boot.test.context.runner.WebApplicationContextRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@DisplayName("AutoConfiguration Tests")
internal class EventAutoConfigurationTests {


    private fun applicationContext(): WebApplicationContextRunner =
            TestApplicationContext.base()
                    .withConfiguration(AutoConfigurations.of(
                            SlackBrokerAutoConfiguration::class.java,
                            TeamStoreAutoconfiguration::class.java,
                            WebMvcAutoConfiguration::class.java))


    @DisplayName("EventBroker")
    @Nested
    internal inner class BrokerTests {

        @DisplayName("EventBroker Registration")
        @Test
        fun eventBrokerRegistration() {
            applicationContext()
                    .withConfiguration(AutoConfigurations.of(
                            EventAutoConfiguration::class.java))
                    .run {
                        Assertions.assertDoesNotThrow { it.getBean(EventBroker::class.java) }
                    }
        }

        @DisplayName("EventArgumentResolver")
        @Test
        fun slackArgumentResolverRegistrations() {
            applicationContext()
                    .withConfiguration(AutoConfigurations.of(
                            EventAutoConfiguration::class.java))
                    .run {
                        val listOf = ArrayList<HandlerMethodArgumentResolver>()
                        val bean = it.getBean<WebMvcConfigurer>("com.kreait.slack.broker.autoconfiguration.event.EventAutoConfiguration\$Broker")

                        bean.addArgumentResolvers(listOf)
                        Assertions.assertTrue(listOf[0] is EventArgumentResolver)
                    }
        }

    }


    @DisplayName("EventStore")
    @Nested
    internal inner class EventStoreTests {

        @DisplayName("InMemoryEventStore Registration")
        @Test
        fun eventStoreRegistration() {
            applicationContext()
                    .withConfiguration(AutoConfigurations.of(
                            EventAutoConfiguration::class.java))
                    .run {
                        Assertions.assertDoesNotThrow { it.getBean(EventStore::class.java) }
                        Assertions.assertTrue(it.getBean(EventStore::class.java) is InMemoryEventStore)
                    }
        }

        @DisplayName("Custom EventStore Registration")
        @Test
        fun customEventStoreRegistration() {
            applicationContext()
                    .withConfiguration(AutoConfigurations.of(
                            EventAutoConfiguration::class.java))
                    .withUserConfiguration(EventStoreTestConfiguration::class.java)
                    .run {
                        Assertions.assertDoesNotThrow { it.getBean(EventStore::class.java) }
                        Assertions.assertTrue(it.getBean(EventStore::class.java) is EventStoreTestConfiguration.TestEventStore)
                    }
        }

    }

    @Configuration
    open class EventStoreTestConfiguration {

        @Bean
        open fun testEventStore(): EventStore = TestEventStore()

        class TestEventStore : EventStore {
            override fun exists(id: String): Boolean = false

            override fun put(event: EventRequest) {}

        }
    }

    @Nested
    @DisplayName("EventMetrics")
    internal inner class EventMetricsTests {

        @DisplayName("EventMetrics Registration")
        @Test
        fun eventMetricsRegistration() {
            applicationContext()
                    .withConfiguration(AutoConfigurations.of(
                            EventAutoConfiguration::class.java))
                    .run {
                        Assertions.assertDoesNotThrow { it.getBean(EventMetricsCollector::class.java) }
                    }
        }

        @DisplayName("EventMetrics Registration Without Micrometer")
        @Test
        fun eventMetricsRegistrationWithoutMicrometer() {
            applicationContext()
                    .withConfiguration(AutoConfigurations.of(
                            EventAutoConfiguration::class.java))
                    .withClassLoader(FilteredClassLoader(MeterRegistry::class.java))
                    .run {
                        Assertions.assertThrows(NoSuchBeanDefinitionException::class.java) { it.getBean(EventMetricsCollector::class.java) }
                    }

        }
    }


}
