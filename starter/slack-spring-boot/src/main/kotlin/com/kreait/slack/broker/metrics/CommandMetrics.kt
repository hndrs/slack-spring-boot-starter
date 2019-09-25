package com.kreait.slack.broker.metrics

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.binder.MeterBinder

/**
 * Collects metrics for incoming commands
 */
interface CommandMetricsCollector {

    /**
     * Increments receiver not matched metric
     */
    fun receiverMismatch()

    /**
     * Increments receiver execution metric
     */
    fun receiverExecuted()

    /**
     * Increments receiver execution metric
     */
    fun receiverExecutionError()

    /**
     * Increments commands received metric
     */
    fun commandsReceived()
}

/**
 * Increments metrics for commands
 */
class CommandMetrics : MeterBinder, CommandMetricsCollector {

    private lateinit var commandReceiverExecutionErrors: Counter
    private lateinit var commandReceiverExecutions: Counter
    private lateinit var commandsReceived: Counter
    private lateinit var commandsReceiverNoneMatch: Counter


    override fun bindTo(registry: MeterRegistry) {

        commandsReceived = Counter.builder("slack.commands.received")
                .description("Total number of commands received")
                .register(registry)

        commandReceiverExecutions = Counter.builder("slack.commands.receiver.executions")
                .description("Total number of command receivers executions")
                .register(registry)

        commandReceiverExecutionErrors = Counter.builder("slack.commands.receiver.errors")
                .description("Number of errors during command receiver execution")
                .register(registry)

        commandsReceiverNoneMatch = Counter.builder("slack.commands.receiver.mismatch")
                .description("Number of times none of the receivers matched")
                .register(registry)
    }

    override fun receiverExecuted() = this.commandReceiverExecutions.increment()

    override fun receiverExecutionError() = this.commandReceiverExecutionErrors.increment()

    override fun commandsReceived() = this.commandsReceived.increment()

    override fun receiverMismatch() = this.commandsReceiverNoneMatch.increment()
}
