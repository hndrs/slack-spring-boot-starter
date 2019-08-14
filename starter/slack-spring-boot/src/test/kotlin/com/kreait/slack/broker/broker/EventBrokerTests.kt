package com.kreait.slack.broker.broker

import com.kreait.slack.api.contract.jackson.SlackEvent
import com.kreait.slack.api.contract.jackson.sample
import com.kreait.slack.broker.extensions.sample
import com.kreait.slack.broker.metrics.EventMetrics
import com.kreait.slack.broker.receiver.EventReceiver
import com.kreait.slack.broker.store.InMemoryEventStore
import com.kreait.slack.broker.store.InMemoryTeamStore
import com.kreait.slack.broker.store.Team
import io.micrometer.core.instrument.simple.SimpleMeterRegistry
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

        val eventStore = InMemoryEventStore()

        teamStore.put(Team.sample().copy(teamId = "TestId"))

        val metrics = EventMetrics()

        val meterRegistry = SimpleMeterRegistry()
        metrics.bindTo(meterRegistry)

        val successReceiver = SuccessReceiver()
        val errorReceiver = ErrorReceiver()

        EventBroker(listOf(successReceiver, errorReceiver), teamStore, eventStore, metrics).receiveEvents(SlackEvent.sample().copy(teamId = "TestId"), HttpHeaders.EMPTY)

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

        val eventStore = InMemoryEventStore()

        teamStore.put(Team.sample().copy(teamId = "TestId"))

        val successReceiver = SuccessReceiver()
        val errorReceiver = ErrorReceiver()

        EventBroker(listOf(successReceiver, errorReceiver), teamStore, eventStore).receiveEvents(SlackEvent.sample().copy(teamId = "TestId"), HttpHeaders.EMPTY)

        Assertions.assertTrue(successReceiver.executed)
        Assertions.assertTrue(errorReceiver.executed)
    }

    @Test
    @DisplayName("Duplicate Event In EventStore")
    fun duplicateEvent() {

        val teamStore = InMemoryTeamStore()

        val sampleEvent = SlackEvent.sample().copy(teamId = "TestId", eventId = "TestEventId")

        val eventStore = InMemoryEventStore()
        // Put sampleEvent in teamStore
        eventStore.put(sampleEvent)

        teamStore.put(Team.sample().copy(teamId = "TestId"))

        val successReceiver = SuccessReceiver()
        val errorReceiver = ErrorReceiver()

        EventBroker(listOf(successReceiver, errorReceiver), teamStore, eventStore).receiveEvents(sampleEvent, HttpHeaders.EMPTY)

        Assertions.assertFalse(successReceiver.executed)
        Assertions.assertFalse(errorReceiver.executed)
    }

    @Test
    @DisplayName("MustThrow exceptions are thrown at the end of the execution")
    fun mustThrow() {

        val teamStore = InMemoryTeamStore()
        teamStore.put(Team.sample().copy(teamId = "TestId"))

        val eventStore = InMemoryEventStore()


        val sampleEvent = SlackEvent.sample().copy(teamId = "TestId", eventId = "TestEventId")
        Assertions.assertThrows(Exception::class.java) {
            EventBroker(listOf(ShouldThrowReceiver(), ShouldThrowReceiver()), teamStore, eventStore).receiveEvents(sampleEvent, HttpHeaders.EMPTY)
        }
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

    class ShouldThrowReceiver : EventReceiver {
        override fun shouldThrowException(exception: Exception): Boolean {
            return true
        }

        override fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team) {
            throw Exception()
        }
    }
}
