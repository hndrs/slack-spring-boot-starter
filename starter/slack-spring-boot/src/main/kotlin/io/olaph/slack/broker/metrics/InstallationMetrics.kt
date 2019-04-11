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
}

class InstallationMetrics : MeterBinder, InstallationMetricsCollector {

    private lateinit var installationSuccessCounter: Counter
    private lateinit var installationErrorCounter: Counter
    private lateinit var installationCounter: Counter

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
    }

    /**
     * Increments successful installation metric
     */
    override fun successfulInstallation() = installationSuccessCounter.increment()

    /**
     * Increments error during installation metric
     */
    override fun errorDuringInstallation() = installationErrorCounter.increment()


    /**
     * Increments installation attempt metric
     */
    override fun installationAttempt() = installationCounter.increment()
}
