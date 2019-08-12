package com.kreait.slack.broker.broker

import com.kreait.slack.api.contract.jackson.InteractiveComponentResponse
import com.kreait.slack.broker.extensions.sample
import com.kreait.slack.broker.metrics.InteractiveComponentMetrics
import com.kreait.slack.broker.receiver.InteractiveComponentReceiver
import com.kreait.slack.broker.store.InMemoryTeamStore
import com.kreait.slack.broker.store.Team
import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders

@DisplayName("Event Broker Tests")
class InteractiveComponentBrokerTests {


    @Test
    @DisplayName("Broker Test")
    fun brokerTest() {

        val teamStore = InMemoryTeamStore()

        teamStore.put(Team.sample().copy(teamId = "TestId"))

        val metrics = InteractiveComponentMetrics()

        val meterRegistry = SimpleMeterRegistry()
        metrics.bindTo(meterRegistry)

        val successReceiver = SuccessReceiver()
        val errorReceiver = ErrorReceiver()

        val componentResponse = InteractiveComponentResponse.sample().copy(team = InteractiveComponentResponse.Team.sample().copy(id = "TestId"))

        InteractiveComponentBroker(listOf(successReceiver, errorReceiver), teamStore, metrics).receiveEvents(componentResponse, HttpHeaders.EMPTY)

        Assertions.assertTrue(successReceiver.executed)
        Assertions.assertTrue(errorReceiver.executed)

        Assertions.assertEquals(1.0, meterRegistry.get("slack.interactive.received").counter().count())
        Assertions.assertEquals(2.0, meterRegistry.get("slack.interactive.receiver.executions").counter().count())
        Assertions.assertEquals(1.0, meterRegistry.get("slack.interactive.receiver.errors").counter().count())
    }


    @Test
    @DisplayName("Broker Test Without Metrics")
    fun brokerTestWithoutMetrics() {

        val teamStore = InMemoryTeamStore()

        teamStore.put(Team.sample().copy(teamId = "TestId"))

        val successReceiver = SuccessReceiver()
        val errorReceiver = ErrorReceiver()

        val componentResponse = InteractiveComponentResponse.sample().copy(team = InteractiveComponentResponse.Team.sample().copy(id = "TestId"))

        InteractiveComponentBroker(listOf(successReceiver, errorReceiver), teamStore).receiveEvents(componentResponse, HttpHeaders.EMPTY)

        Assertions.assertTrue(successReceiver.executed)
        Assertions.assertTrue(errorReceiver.executed)
    }

    class SuccessReceiver : InteractiveComponentReceiver {
        var executed: Boolean = false

        override fun onReceiveInteractiveMessage(interactiveComponentResponse: InteractiveComponentResponse, headers: HttpHeaders, team: Team) {
            executed = true
        }
    }

    class ErrorReceiver : InteractiveComponentReceiver {
        var executed: Boolean = false

        override fun onReceiveInteractiveMessage(interactiveComponentResponse: InteractiveComponentResponse, headers: HttpHeaders, team: Team) {
            executed = true
            throw IllegalStateException("Failing Test Case")
        }
    }
}
