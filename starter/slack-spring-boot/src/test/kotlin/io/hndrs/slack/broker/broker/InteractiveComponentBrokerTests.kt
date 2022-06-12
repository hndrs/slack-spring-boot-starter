package io.hndrs.slack.broker.broker

import io.hndrs.slack.api.contract.jackson.BlockActions
import io.hndrs.slack.api.contract.jackson.InteractiveComponentResponse
import io.hndrs.slack.api.contract.jackson.InteractiveMessage
import io.hndrs.slack.api.contract.jackson.sample
import io.hndrs.slack.broker.extensions.sample
import io.hndrs.slack.broker.interactive.InteractiveComponentBroker
import io.hndrs.slack.broker.metrics.InteractiveComponentMetrics
import io.hndrs.slack.broker.store.team.InMemoryTeamStore
import io.hndrs.slack.broker.store.team.Team
import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders
import java.util.concurrent.atomic.AtomicInteger

@DisplayName("Event Broker Tests")
class InteractiveComponentBrokerTests {
    @Test
    @DisplayName("exceptions are thrown at the end of the execution")
    fun shouldThrow() {
        val teamStore = InMemoryTeamStore()
        teamStore.put(Team.sample().copy(teamId = "TestId"))
        val sampleEvent = InteractiveMessage.sample().copy(team = InteractiveComponentResponse.Team.sample().copy(id = "TestId"))
        Assertions.assertThrows(Exception::class.java) {
            InteractiveComponentBroker(listOf(), listOf(ShouldThrowReceiver(), ShouldThrowReceiver()), teamStore).receiveComponent(sampleEvent, HttpHeaders.EMPTY)
        }
    }

    class ShouldThrowReceiver : InteractiveComponentReceiver<InteractiveMessage> {
        override fun shouldThrowException(exception: Exception): Boolean {
            return true
        }

        override fun onReceiveInteractiveMessage(interactiveComponentResponse: InteractiveMessage, headers: HttpHeaders, team: Team) {
            throw Exception()
        }
    }

    @Test
    @DisplayName("Broker Test")
    fun interactiveMessagebrokerTest() {

        val teamStore = InMemoryTeamStore()

        teamStore.put(Team.sample().copy(teamId = "TestId"))

        val metrics = InteractiveComponentMetrics()

        val meterRegistry = SimpleMeterRegistry()
        metrics.bindTo(meterRegistry)

        val successReceiver = SuccessReceiver()
        val errorReceiver = ErrorReceiver()

        val componentResponse = InteractiveMessage.sample().copy(team = InteractiveComponentResponse.Team.sample().copy(id = "TestId"))

        InteractiveComponentBroker(listOf(), listOf(successReceiver, errorReceiver), teamStore, metrics).receiveComponent(componentResponse, HttpHeaders.EMPTY)

        Assertions.assertTrue(successReceiver.executed)
        Assertions.assertTrue(errorReceiver.executed)

        Assertions.assertEquals(1.0, meterRegistry.get("slack.interactive.received").counter().count())
        Assertions.assertEquals(2.0, meterRegistry.get("slack.interactive.receiver.executions").counter().count())
        Assertions.assertEquals(1.0, meterRegistry.get("slack.interactive.receiver.errors").counter().count())
    }

    @Test
    @DisplayName("BlockAction Broker Test")
    fun BlockActionbrokerTest() {

        val teamStore = InMemoryTeamStore()

        teamStore.put(Team.sample().copy(teamId = "TestId"))

        val metrics = InteractiveComponentMetrics()

        val meterRegistry = SimpleMeterRegistry()
        metrics.bindTo(meterRegistry)

        val successBlockReceiver = SuccessBlockReceiver()
        val errorBlockReceiver = ErrorBlockReceiver()

        val componentResponse = BlockActions.sample().copy(team = InteractiveComponentResponse.Team.sample().copy(id = "TestId"))

        InteractiveComponentBroker(listOf(successBlockReceiver, errorBlockReceiver), listOf(), teamStore, metrics).receiveComponent(componentResponse, HttpHeaders.EMPTY)

        Assertions.assertTrue(successBlockReceiver.executed)
        Assertions.assertTrue(errorBlockReceiver.executed)

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

        val componentResponse = InteractiveMessage.sample().copy(team = InteractiveComponentResponse.Team.sample().copy(id = "TestId"))

        InteractiveComponentBroker(listOf(), listOf(successReceiver, errorReceiver), teamStore).receiveComponent(componentResponse, HttpHeaders.EMPTY)

        Assertions.assertTrue(successReceiver.executed)
        Assertions.assertTrue(errorReceiver.executed)
    }

    @Test
    @DisplayName("Test BlockActionReceiver order")
    fun testComponentReceiverExecutionOrder() {
        val atomic = AtomicInteger(0)
        val first = FirstComponentReceiver(atomic)
        val second = SecondComponentReceiver(atomic)
        val third = ThirdComponentReceiver(atomic)
        val firstBlock = FirstBlockReceiver(atomic)

        val event = InteractiveMessage.sample().copy(team = InteractiveComponentResponse.Team.sample())
                .copy(team = InteractiveComponentResponse.Team.sample().copy(id = "TestTeamId"))
        val store = InMemoryTeamStore()
        store.put(Team.sample().copy(teamId = "TestTeamId"))

        InteractiveComponentBroker(listOf(firstBlock), listOf(third, second, first), store)
                .receiveComponent(event, HttpHeaders.EMPTY)

        Assertions.assertEquals(0, first.currentOrder)
        Assertions.assertEquals(1, second.currentOrder)
        Assertions.assertEquals(2, third.currentOrder)
        Assertions.assertEquals(null, firstBlock.currentOrder)
    }

    @Test
    @DisplayName("Test InteractiveComponentReceiver order")
    fun testBlockActionsReceiverExecutionOrder() {
        val atomic = AtomicInteger(0)
        val firstMessage = FirstComponentReceiver(atomic)
        val first = FirstBlockReceiver(atomic)
        val second = SecondBlockReceiver(atomic)
        val third = ThirdBlockReceiver(atomic)
        val event = BlockActions.sample().copy(team = InteractiveComponentResponse.Team.sample())
                .copy(team = InteractiveComponentResponse.Team.sample().copy(id = "TestTeamId"))
        val store = InMemoryTeamStore()
        store.put(Team.sample().copy(teamId = "TestTeamId"))

        InteractiveComponentBroker(listOf(third, second, first), listOf(firstMessage), store)
                .receiveComponent(event, HttpHeaders.EMPTY)

        Assertions.assertEquals(0, first.currentOrder)
        Assertions.assertEquals(1, second.currentOrder)
        Assertions.assertEquals(2, third.currentOrder)
        Assertions.assertEquals(null, firstMessage.currentOrder)

    }

    class FirstComponentReceiver(private val current: AtomicInteger) : InteractiveComponentReceiver<InteractiveMessage> {
        var currentOrder: Int? = null

        override fun order(): Int {
            return 1
        }

        override fun onReceiveInteractiveMessage(interactiveComponentResponse: InteractiveMessage, headers: HttpHeaders, team: Team) {
            currentOrder = current.getAndIncrement()
        }
    }

    class SecondComponentReceiver(private val current: AtomicInteger) : InteractiveComponentReceiver<InteractiveMessage> {
        var currentOrder: Int? = null
        override fun order(): Int {
            return 2
        }

        override fun onReceiveInteractiveMessage(interactiveComponentResponse: InteractiveMessage, headers: HttpHeaders, team: Team) {
            currentOrder = current.getAndIncrement()
        }
    }

    class ThirdComponentReceiver(private val current: AtomicInteger) : InteractiveComponentReceiver<InteractiveMessage> {
        var currentOrder: Int? = null
        override fun order(): Int {
            return 3
        }

        override fun onReceiveInteractiveMessage(interactiveComponentResponse: InteractiveMessage, headers: HttpHeaders, team: Team) {
            currentOrder = current.getAndIncrement()
        }
    }


    class FirstBlockReceiver(private val current: AtomicInteger) : InteractiveComponentReceiver<BlockActions> {
        var currentOrder: Int? = null

        override fun order(): Int {
            return 1
        }

        override fun onReceiveInteractiveMessage(interactiveComponentResponse: BlockActions, headers: HttpHeaders, team: Team) {
            currentOrder = current.getAndIncrement()
        }
    }

    class SecondBlockReceiver(private val current: AtomicInteger) : InteractiveComponentReceiver<BlockActions> {
        var currentOrder: Int? = null
        override fun order(): Int {
            return 2
        }

        override fun onReceiveInteractiveMessage(interactiveComponentResponse: BlockActions, headers: HttpHeaders, team: Team) {
            currentOrder = current.getAndIncrement()
        }
    }

    class ThirdBlockReceiver(private val current: AtomicInteger) : InteractiveComponentReceiver<BlockActions> {
        var currentOrder: Int? = null
        override fun order(): Int {
            return 3
        }

        override fun onReceiveInteractiveMessage(interactiveComponentResponse: BlockActions, headers: HttpHeaders, team: Team) {
            currentOrder = current.getAndIncrement()
        }
    }


    class SuccessReceiver : InteractiveComponentReceiver<InteractiveMessage> {
        var executed: Boolean = false

        override fun onReceiveInteractiveMessage(interactiveComponentResponse: InteractiveMessage, headers: HttpHeaders, team: Team) {
            executed = true
        }
    }

    class ErrorReceiver : InteractiveComponentReceiver<InteractiveMessage> {
        var executed: Boolean = false

        override fun onReceiveInteractiveMessage(interactiveComponentResponse: InteractiveMessage, headers: HttpHeaders, team: Team) {
            executed = true
            throw IllegalStateException("Failing Test Case")
        }
    }

    class SuccessBlockReceiver : InteractiveComponentReceiver<BlockActions> {
        var executed: Boolean = false

        override fun onReceiveInteractiveMessage(interactiveComponentResponse: BlockActions, headers: HttpHeaders, team: Team) {
            executed = true
        }
    }

    class ErrorBlockReceiver : InteractiveComponentReceiver<BlockActions> {
        var executed: Boolean = false

        override fun onReceiveInteractiveMessage(interactiveComponentResponse: BlockActions, headers: HttpHeaders, team: Team) {
            executed = true
            throw IllegalStateException("Failing Test Case")
        }
    }
}
