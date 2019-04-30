package io.olaph.slack.broker.autoconfiguration

import org.springframework.boot.test.context.runner.WebApplicationContextRunner

object TestApplicationContext {

    fun base(): WebApplicationContextRunner {
        return WebApplicationContextRunner()
                .withSystemProperties("slack.application.client-id:id",
                        "slack.application.client-secret:secret",
                        "slack.application.signing-secret:signingsecret")
    }
}
