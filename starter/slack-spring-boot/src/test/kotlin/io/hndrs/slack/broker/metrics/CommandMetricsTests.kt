package io.hndrs.slack.broker.metrics

import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


class CommandMetricsTests {

    @Test
    @DisplayName("Comamnd MetricsTests")
    fun testCommandMetrics() {

        val metrics = CommandMetrics()
        val simpleMeterRegistry = SimpleMeterRegistry()

        metrics.bindTo(simpleMeterRegistry)

        metrics.commandsReceived()
        metrics.receiverExecuted()
        metrics.receiverExecutionError()


        Assertions.assertEquals(1.0, simpleMeterRegistry.get("slack.commands.received").counter().count())
        Assertions.assertEquals(1.0, simpleMeterRegistry.get("slack.commands.receiver.executions").counter().count())
        Assertions.assertEquals(1.0, simpleMeterRegistry.get("slack.commands.receiver.errors").counter().count())
    }
}
