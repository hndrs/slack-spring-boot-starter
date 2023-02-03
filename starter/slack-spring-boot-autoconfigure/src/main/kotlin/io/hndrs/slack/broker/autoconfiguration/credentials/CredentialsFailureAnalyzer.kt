package io.hndrs.slack.broker.autoconfiguration.credentials

import org.springframework.beans.BeanInstantiationException
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer
import org.springframework.boot.diagnostics.FailureAnalysis

/**
 * [org.springframework.boot.diagnostics.FailureAnalyzer] that helps identifying [CredentialsProvider] issues
 */
class CredentialsFailureAnalyzer : AbstractFailureAnalyzer<BeanInstantiationException>() {

    override fun analyze(rootFailure: Throwable?, cause: BeanInstantiationException): FailureAnalysis? {
        return if (cause.cause is ApplicationCredentialsException) {
            return FailureAnalysis(DESCRIPTION, ACTION, cause.cause)
        } else {
            null
        }
    }

    companion object {
        private const val DESCRIPTION = "Could not resolve slack app credentials"

        private const val ACTION = "Consider setting the credentials"
    }
}
