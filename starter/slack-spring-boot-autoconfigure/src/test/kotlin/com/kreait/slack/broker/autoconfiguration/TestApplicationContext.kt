package com.kreait.slack.broker.autoconfiguration

import org.springframework.boot.autoconfigure.logging.ConditionEvaluationReportLoggingListener
import org.springframework.boot.test.context.runner.WebApplicationContextRunner

object TestApplicationContext {

    fun base(): WebApplicationContextRunner {
        return WebApplicationContextRunner()
                .withInitializer(ConditionEvaluationReportLoggingListener())
                .withSystemProperties("slack.application.client-id:id",
                        "slack.application.client-secret:secret",
                        "slack.application.signing-secret:signingsecret")
    }
}
