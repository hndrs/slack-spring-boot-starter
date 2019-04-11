package io.olaph.slack.broker.metrics

import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class EventMetricsTests {


    @Test
    @DisplayName("Event MetricsTests")
    fun testEventMetrics() {

        val metrics = EventMetrics()
        val simpleMeterRegistry = SimpleMeterRegistry()

        metrics.bindTo(simpleMeterRegistry)

        metrics.eventsReceived()
        metrics.receiverExecuted()
        metrics.receiverExecutionError()


        Assertions.assertEquals(1.0, simpleMeterRegistry.get("slack.events.received").counter().count())
        Assertions.assertEquals(1.0, simpleMeterRegistry.get("slack.events.receiver.executions").counter().count())
        Assertions.assertEquals(1.0, simpleMeterRegistry.get("slack.events.receiver.errors").counter().count())
    }
}
