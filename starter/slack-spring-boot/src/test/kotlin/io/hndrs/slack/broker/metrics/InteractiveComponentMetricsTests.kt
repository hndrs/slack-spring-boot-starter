package io.hndrs.slack.broker.metrics

import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


class InteractiveComponentMetricsTests {

    @DisplayName("InteractiveComponent MetricsTests")
    @Test
    fun testInteractiveComponentMetrics() {

        val metrics = InteractiveComponentMetrics()
        val simpleMeterRegistry = SimpleMeterRegistry()

        metrics.bindTo(simpleMeterRegistry)

        metrics.responseReceived()
        metrics.receiverExecuted()
        metrics.receiverExecutionError()


        Assertions.assertEquals(1.0, simpleMeterRegistry.get("slack.interactive.received").counter().count())
        Assertions.assertEquals(1.0, simpleMeterRegistry.get("slack.interactive.receiver.executions").counter().count())
        Assertions.assertEquals(1.0, simpleMeterRegistry.get("slack.interactive.receiver.errors").counter().count())
    }
}
