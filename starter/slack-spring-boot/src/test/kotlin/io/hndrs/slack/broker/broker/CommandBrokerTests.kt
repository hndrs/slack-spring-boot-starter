package io.hndrs.slack.broker.broker

import io.hndrs.slack.api.contract.jackson.sample
import io.hndrs.slack.broker.command.CommandBroker
import io.hndrs.slack.broker.extensions.sample
import io.hndrs.slack.broker.metrics.CommandMetrics
import io.hndrs.slack.broker.receiver.MismatchCommandReceiver
import io.hndrs.slack.broker.receiver.SlashCommandReceiver
import io.hndrs.slack.broker.store.team.InMemoryTeamStore
import io.hndrs.slack.broker.store.team.Team
import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders
import java.util.concurrent.atomic.AtomicInteger

@DisplayName("Command Broker Tests")
class CommandBrokerTests {

    @Test
    @DisplayName("exceptions are thrown at the end of the execution")
    fun shouldThrow() {
        val teamStore = InMemoryTeamStore()
        teamStore.put(Team.sample().copy(teamId = "TestId"))
        val sampleEvent = SlackCommand.sample().copy(teamId = "TestId")
        Assertions.assertThrows(Exception::class.java) {
            CommandBroker(listOf(ShouldThrowReceiver(), ShouldThrowReceiver()), teamStore).receiveCommand(sampleEvent, HttpHeaders.EMPTY)
        }
    }

    class ShouldThrowReceiver : SlashCommandReceiver {
        override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
            throw Exception()
        }

        override fun shouldThrowException(exception: Exception): Boolean {
            return true
        }

    }

    @Test
    @DisplayName("Broker Test")
    fun brokerTest() {
        val teamStore = InMemoryTeamStore()

        teamStore.put(Team.sample().copy(teamId = "TestId"))

        val commandMetrics = CommandMetrics()

        val meterRegistry = SimpleMeterRegistry()
        commandMetrics.bindTo(meterRegistry)

        val successReceiver = SuccessReceiver()
        val errorReceiver = ErrorReceiver()
        val mismatchReceiver = Mismatch()

        CommandBroker(listOf(successReceiver, errorReceiver), teamStore, null, commandMetrics)
            .receiveCommand(SlackCommand.sample().copy(teamId = "TestId"), HttpHeaders.EMPTY)
        CommandBroker(listOf(), teamStore, mismatchReceiver, commandMetrics)
            .receiveCommand(SlackCommand.sample().copy(teamId = "TestId"), HttpHeaders.EMPTY)

        Assertions.assertTrue(successReceiver.executed)
        Assertions.assertTrue(errorReceiver.executed)
        Assertions.assertTrue(mismatchReceiver.executed)

        Assertions.assertEquals(2.0, meterRegistry.get("slack.commands.received").counter().count())
        Assertions.assertEquals(2.0, meterRegistry.get("slack.commands.receiver.executions").counter().count())
        Assertions.assertEquals(1.0, meterRegistry.get("slack.commands.receiver.errors").counter().count())
        Assertions.assertEquals(1.0, meterRegistry.get("slack.commands.receiver.mismatch").counter().count())
    }


    @Test
    @DisplayName("Broker Test Without Metrics")
    fun brokerTestWithoutMetrics() {

        val teamStore = InMemoryTeamStore()

        teamStore.put(Team.sample().copy(teamId = "TestId"))

        val successReceiver = SuccessReceiver()
        val errorReceiver = ErrorReceiver()

        CommandBroker(listOf(successReceiver, errorReceiver), teamStore)
            .receiveCommand(SlackCommand.sample().copy(teamId = "TestId"), HttpHeaders.EMPTY)


        Assertions.assertTrue(successReceiver.executed)
        Assertions.assertTrue(errorReceiver.executed)
    }

    @Test
    @DisplayName("Test Command Receiver order")
    fun testCommandReceiverExecutionOrder() {
        val atomic = AtomicInteger(0)
        val first = FirstCommandReceiver(atomic)
        val second = SecondCommandReceiver(atomic)
        val third = ThirdCommandReceiver(atomic)
        val event = SlackCommand.sample().copy(teamId = "TestTeamId")
        val store = InMemoryTeamStore()
        store.put(Team.sample().copy(teamId = "TestTeamId"))

        CommandBroker(listOf(third, second, first), store)
                .receiveCommand(event, HttpHeaders.EMPTY)

        Assertions.assertEquals(0, first.currentOrder)
        Assertions.assertEquals(1, second.currentOrder)
        Assertions.assertEquals(2, third.currentOrder)
    }

    class FirstCommandReceiver(private val current: AtomicInteger) : SlashCommandReceiver {
        var currentOrder: Int? = null

        override fun order(): Int {
            return 1
        }

        override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
            currentOrder = current.getAndIncrement()
        }
    }

    class SecondCommandReceiver(private val current: AtomicInteger) : SlashCommandReceiver {
        var currentOrder: Int? = null
        override fun order(): Int {
            return 2
        }

        override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
            currentOrder = current.getAndIncrement()
        }
    }

    class ThirdCommandReceiver(private val current: AtomicInteger) : SlashCommandReceiver {
        var currentOrder: Int? = null
        override fun order(): Int {
            return 3
        }

        override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
            currentOrder = current.getAndIncrement()
        }
    }


    class SuccessReceiver : SlashCommandReceiver {

        var executed: Boolean = false

        override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
            executed = true
        }

    }

    class ErrorReceiver : SlashCommandReceiver {

        var executed: Boolean = false

        override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
            executed = true
            throw IllegalStateException("Failing Test Case")
        }

    }

    class Mismatch : MismatchCommandReceiver {

        var executed: Boolean = false
        override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
            executed = true
        }

    }
}
