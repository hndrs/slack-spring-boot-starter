package io.hndrs.slack.broker.autoconfiguration

import io.hndrs.slack.broker.interactive.InteractiveComponentBroker
import io.hndrs.slack.broker.store.team.InMemoryTeamStore
import io.kotest.assertions.throwables.shouldNotThrow
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.WebApplicationContextRunner

class InteractiveComponentAutoConfigurationTest {

    @Test
    fun `interactive component controller is registered`() {
        WebApplicationContextRunner()
            .withBean(InMemoryTeamStore::class.java)
            .withConfiguration(
                AutoConfigurations.of(InteractiveComponentAutoConfiguration::class.java)
            ).run {
                shouldNotThrow<Exception> {
                    it.getBean(InteractiveComponentBroker::class.java)
                }
            }
    }
}
