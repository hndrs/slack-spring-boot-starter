package io.hndrs.slack.broker.broker

import com.slack.api.model.event.UserChangeEvent
import io.hndrs.slack.broker.event.EventHandler
import io.hndrs.slack.broker.event.SlackChallenge
import io.hndrs.slack.broker.event.SlackEvent
import io.hndrs.slack.broker.event.http.EventEndpoint
import io.hndrs.slack.broker.extensions.sample
import io.hndrs.slack.broker.sample
import io.hndrs.slack.broker.store.event.InMemoryEventStore
import io.hndrs.slack.broker.store.team.InMemoryTeamStore
import io.hndrs.slack.broker.store.team.Team
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

@DisplayName("Event Broker Tests")
class EventEndpointTests {

    @Test
    @DisplayName("Broker Test")
    fun brokerTest() {
        val teamStore = InMemoryTeamStore()

        val eventStore = InMemoryEventStore()

        teamStore.put(Team.sample().copy(teamId = "TestId"))

        val successReceiver = SuccessHandler()
        val errorReceiver = ErrorHandler()

        EventEndpoint(listOf(successReceiver, errorReceiver), teamStore, eventStore)
            .receiveEvents(
                SlackEvent.sample(UserChangeEvent())
                    .copy(teamId = "TestId"),
                HttpHeaders()
            )

        Assertions.assertTrue(successReceiver.executed)
        Assertions.assertTrue(errorReceiver.executed)
    }

    @Test
    @DisplayName("SlackChallengeTest")
    fun testSlackChallenge() {
        val random = UUID.randomUUID().toString()
        val receiveEvents = EventEndpoint(listOf(), InMemoryTeamStore(), InMemoryEventStore())
            .receiveEvents(SlackChallenge.sample().copy(challenge = random), HttpHeaders.EMPTY)
        Assertions.assertEquals(random, receiveEvents["challenge"])
    }

    @Test
    @DisplayName("Broker Test Without Metrics")
    fun brokerTestWithoutMetrics() {
        val teamStore = InMemoryTeamStore()

        val eventStore = InMemoryEventStore()

        teamStore.put(Team.sample().copy(teamId = "TestId"))

        val successReceiver = SuccessHandler()
        val errorReceiver = ErrorHandler()

        EventEndpoint(listOf(successReceiver, errorReceiver), teamStore, eventStore)
            .receiveEvents(
                SlackEvent.sample(UserChangeEvent())
                    .copy(teamId = "TestId"),
                HttpHeaders.EMPTY
            )

        Assertions.assertTrue(successReceiver.executed)
        Assertions.assertTrue(errorReceiver.executed)
    }

    @Test
    @DisplayName("Duplicate Event In EventStore")
    fun duplicateEvent() {
        val teamStore = InMemoryTeamStore()

        val sampleEvent = SlackEvent.sample(UserChangeEvent())
            .copy(teamId = "TestId", eventId = "TestEventId")

        val eventStore = InMemoryEventStore()
        // Put sampleEvent in teamStore
        eventStore.put(sampleEvent)

        teamStore.put(Team.sample().copy(teamId = "TestId"))

        val successReceiver = SuccessHandler()
        val errorReceiver = ErrorHandler()

        val listOf = listOf(successReceiver, errorReceiver)

        EventEndpoint(listOf(successReceiver, errorReceiver), teamStore, eventStore).receiveEvents(
            sampleEvent,
            HttpHeaders.EMPTY
        )

        Assertions.assertFalse(successReceiver.executed)
        Assertions.assertFalse(errorReceiver.executed)
    }

    @Test
    @DisplayName("MustThrow exceptions are thrown at the end of the execution")
    fun mustThrow() {
        val teamStore = InMemoryTeamStore()
        teamStore.put(Team.sample().copy(teamId = "TestId"))

        val eventStore = InMemoryEventStore()

        val sampleEvent = SlackEvent.sample(UserChangeEvent()).copy(teamId = "TestId", eventId = "TestEventId")
        Assertions.assertThrows(Exception::class.java) {
            EventEndpoint(listOf(ShouldThrowHandler(), ShouldThrowHandler()), teamStore, eventStore).receiveEvents(
                sampleEvent,
                HttpHeaders.EMPTY
            )
        }
    }

    @Test
    @DisplayName("Test Eventreceiver")
    fun testEventReceiverExecutionOrder() {
        val atomic = AtomicInteger(0)
        val first = FirstEventHandler(atomic)
        val second = SecondEventHandler(atomic)
        val third = ThirdEventHandler(atomic)
        val event = SlackEvent.sample(UserChangeEvent()).copy(teamId = "TestTeamId")
        val store = InMemoryTeamStore()
        store.put(Team.sample().copy(teamId = "TestTeamId"))

        EventEndpoint(listOf(third, second, first), store, InMemoryEventStore())
            .receiveEvents(event, HttpHeaders.EMPTY)

        Assertions.assertEquals(0, first.currentOrder)
        Assertions.assertEquals(1, second.currentOrder)
        Assertions.assertEquals(2, third.currentOrder)
    }

    class FirstEventHandler(private val current: AtomicInteger) : EventHandler {
        override fun order(): Int {
            return 1
        }

        var currentOrder: Int? = null
        override fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team) {
            currentOrder = current.getAndIncrement()
        }
    }

    class SecondEventHandler(private val current: AtomicInteger) : EventHandler {
        override fun order(): Int {
            return 2
        }

        var currentOrder: Int? = null
        override fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team) {
            currentOrder = current.getAndIncrement()
        }
    }

    class ThirdEventHandler(private val current: AtomicInteger) : EventHandler {
        override fun order(): Int {
            return 3
        }

        var currentOrder: Int? = null
        override fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team) {
            currentOrder = current.getAndIncrement()
        }
    }

    class SuccessHandler : EventHandler {
        var executed: Boolean = false

        override fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team) {
            executed = true
        }
    }

    class ErrorHandler : EventHandler {

        var executed: Boolean = false

        override fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team) {
            executed = true
            error("Failing Test Case")
        }
    }

    class ShouldThrowHandler : EventHandler {
        override fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team) {
            error("Simulated error")
        }

        override fun shouldThrowException(exception: Exception): Boolean {
            return true
        }
    }
}
