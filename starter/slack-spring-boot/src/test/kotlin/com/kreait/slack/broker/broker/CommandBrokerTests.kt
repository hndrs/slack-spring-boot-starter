package com.kreait.slack.broker.broker

import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import com.kreait.slack.broker.extensions.sample
import com.kreait.slack.broker.metrics.CommandMetrics
import com.kreait.slack.broker.receiver.MismatchCommandReciever
import com.kreait.slack.broker.receiver.SlashCommandReceiver
import com.kreait.slack.broker.store.InMemoryTeamStore
import com.kreait.slack.broker.store.Team
import com.kreait.slack.api.contract.jackson.SlackCommand
import com.kreait.slack.api.contract.jackson.sample
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders

@DisplayName("Command Broker Tests")
class CommandBrokerTests {


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

        CommandBroker(listOf(successReceiver, errorReceiver), teamStore,null, commandMetrics).receiveCommand(SlackCommand.sample().copy(teamId = "TestId"), HttpHeaders.EMPTY)
        CommandBroker(listOf(), teamStore, mismatchReceiver, commandMetrics).receiveCommand(SlackCommand.sample().copy(teamId = "TestId"), HttpHeaders.EMPTY)

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

        CommandBroker(listOf(successReceiver, errorReceiver), teamStore).receiveCommand(SlackCommand.sample().copy(teamId = "TestId"), HttpHeaders.EMPTY)


        Assertions.assertTrue(successReceiver.executed)
        Assertions.assertTrue(errorReceiver.executed)
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

    class Mismatch : MismatchCommandReciever {

        var executed: Boolean = false
        override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
            executed = true
        }

    }
}
