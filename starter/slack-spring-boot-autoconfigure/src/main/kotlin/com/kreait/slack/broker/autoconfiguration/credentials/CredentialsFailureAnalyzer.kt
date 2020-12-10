package com.kreait.slack.broker.autoconfiguration.credentials

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
        private val DESCRIPTION = "Could not resolve slack app credentials"

        //TODO add documentation url here
        private val ACTION = "Consider setting the credentials"
    }
}
