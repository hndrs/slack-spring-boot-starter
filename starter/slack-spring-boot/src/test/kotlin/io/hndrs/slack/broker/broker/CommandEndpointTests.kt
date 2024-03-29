package io.hndrs.slack.broker.broker

import com.slack.api.Slack
import io.hndrs.slack.broker.command.CommandEndpoint
import io.hndrs.slack.broker.command.CommandHandler
import io.hndrs.slack.broker.command.SlashCommand
import io.hndrs.slack.broker.command.UnknownCommandHandler
import io.hndrs.slack.broker.extensions.sample
import io.hndrs.slack.broker.sample
import io.hndrs.slack.broker.store.team.InMemoryTeamStore
import io.hndrs.slack.broker.store.team.Team
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders
import java.util.concurrent.atomic.AtomicInteger

@DisplayName("Command Broker Tests")
class CommandEndpointTests {

    @Test
    @DisplayName("exceptions are thrown at the end of the execution")
    fun shouldThrow() {
        val teamStore = InMemoryTeamStore()
        teamStore.put(Team.sample().copy(teamId = "TestId"))
        val sampleEvent = SlashCommand.sample().copy(teamId = "TestId")
        Assertions.assertThrows(Exception::class.java) {
            CommandEndpoint(listOf(ShouldThrowHandler(), ShouldThrowHandler()), teamStore, mockk()).receiveCommand(
                sampleEvent,
                HttpHeaders.EMPTY
            )
        }
    }

    class ShouldThrowHandler : CommandHandler {
        override fun onSlashCommand(slashCommand: SlashCommand, headers: HttpHeaders, team: Team) {
            error("Simulated error")
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

        val slack = mockk<Slack> {
            every { methods(any(), any()) } returns mockk()
        }

        val successReceiver = SuccessHandler()
        val errorReceiver = ErrorHandler()
        val mismatchReceiver = Unknown()

        CommandEndpoint(listOf(successReceiver, errorReceiver), teamStore)
            .receiveCommand(SlashCommand.sample().copy(teamId = "TestId"), HttpHeaders.EMPTY)

        CommandEndpoint(listOf(), teamStore, mismatchReceiver)
            .receiveCommand(SlashCommand.sample().copy(teamId = "TestId"), HttpHeaders.EMPTY)

        Assertions.assertTrue(successReceiver.executed)
        Assertions.assertTrue(errorReceiver.executed)
        Assertions.assertTrue(mismatchReceiver.executed)
    }

    @Test
    @DisplayName("Broker Test Without Metrics")
    fun brokerTestWithoutMetrics() {
        val teamStore = InMemoryTeamStore()

        teamStore.put(Team.sample().copy(teamId = "TestId"))

        val successReceiver = SuccessHandler()
        val errorReceiver = ErrorHandler()

        CommandEndpoint(listOf(successReceiver, errorReceiver), teamStore, mockk(relaxed = true))
            .receiveCommand(SlashCommand.sample().copy(teamId = "TestId"), HttpHeaders.EMPTY)

        Assertions.assertTrue(successReceiver.executed)
        Assertions.assertTrue(errorReceiver.executed)
    }

    @Test
    @DisplayName("Test Command Receiver order")
    fun testCommandReceiverExecutionOrder() {
        val atomic = AtomicInteger(0)
        val first = FirstCommandHandler(atomic)
        val second = SecondCommandHandler(atomic)
        val third = ThirdCommandHandler(atomic)
        val event = SlashCommand.sample().copy(teamId = "TestTeamId")
        val store = InMemoryTeamStore()
        store.put(Team.sample().copy(teamId = "TestTeamId"))

        CommandEndpoint(listOf(third, second, first), store, mockk())
            .receiveCommand(event, HttpHeaders.EMPTY)

        Assertions.assertEquals(0, first.currentOrder)
        Assertions.assertEquals(1, second.currentOrder)
        Assertions.assertEquals(2, third.currentOrder)
    }

    class FirstCommandHandler(private val current: AtomicInteger) : CommandHandler {
        var currentOrder: Int? = null

        override fun order(): Int {
            return 1
        }

        override fun onSlashCommand(slashCommand: SlashCommand, headers: HttpHeaders, team: Team) {
            currentOrder = current.getAndIncrement()
        }
    }

    class SecondCommandHandler(private val current: AtomicInteger) : CommandHandler {
        var currentOrder: Int? = null
        override fun order(): Int {
            return 2
        }

        override fun onSlashCommand(slashCommand: SlashCommand, headers: HttpHeaders, team: Team) {
            currentOrder = current.getAndIncrement()
        }
    }

    class ThirdCommandHandler(private val current: AtomicInteger) : CommandHandler {
        var currentOrder: Int? = null
        override fun order(): Int {
            return 3
        }

        override fun onSlashCommand(slashCommand: SlashCommand, headers: HttpHeaders, team: Team) {
            currentOrder = current.getAndIncrement()
        }
    }

    class SuccessHandler : CommandHandler {

        var executed: Boolean = false

        override fun onSlashCommand(slashCommand: SlashCommand, headers: HttpHeaders, team: Team) {
            executed = true
        }
    }

    class ErrorHandler : CommandHandler {

        var executed: Boolean = false

        override fun onSlashCommand(slashCommand: SlashCommand, headers: HttpHeaders, team: Team) {
            executed = true
            error("Failing Test Case")
        }
    }

    class Unknown : UnknownCommandHandler {

        var executed: Boolean = false
        override fun onUnknownCommand(
            slashCommand: SlashCommand,
            headers: HttpHeaders,
            team: Team,
        ) {
            executed = true
        }
    }
}
