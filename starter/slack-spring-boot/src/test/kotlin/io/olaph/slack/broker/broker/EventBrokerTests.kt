package io.olaph.slack.broker.broker

import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import io.olaph.slack.broker.extensions.sample
import io.olaph.slack.broker.metrics.EventMetrics
import io.olaph.slack.broker.receiver.EventReceiver
import io.olaph.slack.broker.store.InMemoryTeamStore
import io.olaph.slack.broker.store.Team
import io.olaph.slack.dto.jackson.SlackEvent
import io.olaph.slack.dto.jackson.sample
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders

@DisplayName("Event Broker Tests")
class EventBrokerTests {


    @Test
    @DisplayName("Broker Test")
    fun brokerTest() {

        val teamStore = InMemoryTeamStore()

        teamStore.put(Team.sample().copy(teamId = "TestId"))

        val metrics = EventMetrics()

        val meterRegistry = SimpleMeterRegistry()
        metrics.bindTo(meterRegistry)

        val successReceiver = SuccessReceiver()
        val errorReceiver = ErrorReceiver()

        EventBroker(listOf(successReceiver, errorReceiver), teamStore, metrics).receiveEvents(SlackEvent.sample().copy(teamId = "TestId"), HttpHeaders.EMPTY)

        Assertions.assertTrue(successReceiver.executed)
        Assertions.assertTrue(errorReceiver.executed)

        Assertions.assertEquals(1.0, meterRegistry.get("slack.events.received").counter().count())
        Assertions.assertEquals(2.0, meterRegistry.get("slack.events.receiver.executions").counter().count())
        Assertions.assertEquals(1.0, meterRegistry.get("slack.events.receiver.errors").counter().count())
    }


    @Test
    @DisplayName("Broker Test Without Metrics")
    fun brokerTestWithoutMetrics() {

        val teamStore = InMemoryTeamStore()

        teamStore.put(Team.sample().copy(teamId = "TestId"))

        val successReceiver = SuccessReceiver()
        val errorReceiver = ErrorReceiver()

        EventBroker(listOf(successReceiver, errorReceiver), teamStore).receiveEvents(SlackEvent.sample().copy(teamId = "TestId"), HttpHeaders.EMPTY)

        Assertions.assertTrue(successReceiver.executed)
        Assertions.assertTrue(errorReceiver.executed)
    }

    class SuccessReceiver : EventReceiver {

        var executed: Boolean = false

        override fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team) {
            executed = true
        }


    }

    class ErrorReceiver : EventReceiver {

        var executed: Boolean = false

        override fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team) {
            executed = true
            throw IllegalStateException("Failing Test Case")
        }

    }
}
