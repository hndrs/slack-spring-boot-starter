package io.olaph.slack.broker.metrics

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.binder.MeterBinder


interface InstallationMetricsCollector {
    /**
     * Increments successful installation metric
     */
    fun successfulInstallation()

    /**
     * Increments error during installation metric
     */
    fun errorDuringInstallation()


    /**
     * Increments installation attempt metric
     */
    fun installationAttempt()

    /**
     * Increments receiver execution metric
     */
    fun receiverExecuted()

    /**
     * Increments receiver execution metric
     */
    fun receiverExecutionError()
}

class InstallationMetrics : MeterBinder, InstallationMetricsCollector {

    private lateinit var installationSuccessCounter: Counter
    private lateinit var installationErrorCounter: Counter
    private lateinit var installationCounter: Counter
    private lateinit var installationReceiverExecutionErrors: Counter
    private lateinit var installationReceiverExecutions: Counter

    override fun bindTo(registry: MeterRegistry) {

        installationSuccessCounter = Counter.builder("slack.installation.success")
                .description("Number of successful installations")
                .register(registry)

        installationErrorCounter = Counter.builder("slack.installation.error")
                .description("Number of errors during installations")
                .register(registry)

        installationCounter = Counter.builder("slack.installation.count")
                .description("Number of installation attempts")
                .register(registry)

        installationReceiverExecutions = Counter.builder("slack.installation.receiver.executions")
                .description("Total number of installation receivers executions")
                .register(registry)

        installationReceiverExecutionErrors = Counter.builder("slack.installation.receiver.errors")
                .description("Number of errors during installation receiver execution")
                .register(registry)
    }

    override fun successfulInstallation() = installationSuccessCounter.increment()

    override fun errorDuringInstallation() = installationErrorCounter.increment()

    override fun installationAttempt() = installationCounter.increment()

    override fun receiverExecuted() = installationReceiverExecutions.increment()

    override fun receiverExecutionError() = installationReceiverExecutionErrors.increment()
}
