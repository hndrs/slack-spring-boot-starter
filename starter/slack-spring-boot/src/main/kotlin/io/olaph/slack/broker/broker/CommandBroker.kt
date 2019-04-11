package io.olaph.slack.broker.broker

import io.olaph.slack.broker.configuration.Command
import io.olaph.slack.broker.metrics.CommandMetricsCollector
import io.olaph.slack.broker.receiver.SlashCommandReceiver
import io.olaph.slack.broker.store.TeamStore
import io.olaph.slack.dto.jackson.SlackCommand
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class CommandBroker constructor(private val slackCommandReceivers: List<SlashCommandReceiver>,
                                private val teamStore: TeamStore,
                                private val metricsCollector: CommandMetricsCollector?) {

    companion object {
        val LOG = LoggerFactory.getLogger(CommandBroker::class.java)
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/commands", consumes = ["application/x-www-form-urlencoded"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun receiveCommand(@Command slackCommand: SlackCommand, @RequestHeader headers: HttpHeaders) {

        this.metricsCollector?.commandsReceived()

        val team = this.teamStore.findById(slackCommand.teamId)
        slackCommandReceivers
                .filter { broker -> broker.supportsCommand(slackCommand) }
                .forEach { broker ->
                    try {
                        this.metricsCollector?.receiverExecuted()
                        broker.onReceiveSlashCommand(slackCommand, headers, team)
                    } catch (e: Exception) {
                        this.metricsCollector?.receiverExecutionError()
                        LOG.error("{}",e)
                    }

                }
    }
}
