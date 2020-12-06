package com.kreait.slack.broker.autoconfiguration.credentials

import org.springframework.beans.BeanInstantiationException
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer
import org.springframework.boot.diagnostics.FailureAnalysis

class CredentialsFailureAnalyzer : AbstractFailureAnalyzer<BeanInstantiationException>() {

    override fun analyze(rootFailure: Throwable?, cause: BeanInstantiationException): FailureAnalysis? {
        return if (cause.cause is ApplicationCredentialsException) {
            return FailureAnalysis("Could Not Resolve Credentials", "Consider doing this", cause.cause)
        } else {
            null
        }
    }
}