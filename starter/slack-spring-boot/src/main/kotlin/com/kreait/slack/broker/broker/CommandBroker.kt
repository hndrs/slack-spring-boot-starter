package com.kreait.slack.broker.broker

import com.kreait.slack.api.contract.jackson.SlackCommand
import com.kreait.slack.broker.configuration.Command
import com.kreait.slack.broker.metrics.CommandMetricsCollector
import com.kreait.slack.broker.receiver.MismatchCommandReceiver
import com.kreait.slack.broker.receiver.SlashCommandReceiver
import com.kreait.slack.broker.store.team.TeamStore
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

/**
 * CommandBroker that forwards incoming [SlackCommand]s to all [SlashCommandReceiver]s
 */
@SuppressWarnings("detekt:TooGenericExceptionCaught")
@RestController
class CommandBroker constructor(private val slackCommandReceivers: List<SlashCommandReceiver>,
                                private val teamStore: TeamStore,
                                private val mismatchCommandReceiver: MismatchCommandReceiver? = null,
                                private val metricsCollector: CommandMetricsCollector? = null) {

    companion object {
        private val LOG = LoggerFactory.getLogger(CommandBroker::class.java)
    }

    /**
     * Endpoint that receives the commands
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/commands", consumes = ["application/x-www-form-urlencoded"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun receiveCommand(@Command slackCommand: SlackCommand, @RequestHeader headers: HttpHeaders) {

        this.metricsCollector?.commandsReceived()

        val team = this.teamStore.findById(slackCommand.teamId)
        slackCommandReceivers
                .filter { it.supportsCommand(slackCommand) }
                .sortedBy { it.order() }
                .ifEmpty {
                    this.metricsCollector?.receiverMismatch()
                    mismatchCommandReceiver?.onReceiveSlashCommand(slackCommand, headers, team)
                    listOf()
                }
                .forEach { broker ->
                    try {
                        this.metricsCollector?.receiverExecuted()
                        broker.onReceiveSlashCommand(slackCommand, headers, team)
                    } catch (e: Exception) {
                        this.metricsCollector?.receiverExecutionError()
                        if (broker.shouldThrowException(e)) {
                            throw e
                        }
                    }
                }
    }
}
