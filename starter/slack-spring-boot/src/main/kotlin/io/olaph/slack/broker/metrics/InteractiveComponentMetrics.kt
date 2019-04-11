package io.olaph.slack.broker.metrics

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.binder.MeterBinder

interface InteractiveComponentMetricsCollector {

    /**
     * Increments receivers executed metric
     */
    fun receiverExecuted()

    /**
     * Increments execution error metric
     */
    fun receiverExecutionError()

    /**
     * Increments received metric
     */
    fun responseReceived()
}

class InteractiveComponentMetrics : MeterBinder, InteractiveComponentMetricsCollector {

    private lateinit var responseReceiverExecutionErrors: Counter
    private lateinit var responseReceiverExecutions: Counter
    private lateinit var responseReceived: Counter

    override fun bindTo(registry: MeterRegistry) {

        responseReceived = Counter.builder("slack.interactive.received")
                .description("Total number of interactive components received")
                .register(registry)

        responseReceiverExecutions = Counter.builder("slack.interactive.receiver.executions")
                .description("Total number of interactive component receivers executions")
                .register(registry)

        responseReceiverExecutionErrors = Counter.builder("slack.interactive.receiver.errors")
                .description("Number of errors during interactive component receiver execution")
                .register(registry)
    }

    override fun receiverExecuted() = this.responseReceiverExecutions.increment()

    override fun receiverExecutionError() = this.responseReceiverExecutionErrors.increment()

    override fun responseReceived() = this.responseReceived.increment()
}
