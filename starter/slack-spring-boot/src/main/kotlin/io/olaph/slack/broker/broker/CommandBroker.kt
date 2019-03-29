package io.olaph.slack.broker.broker

import io.olaph.slack.broker.configuration.Command
import io.olaph.slack.broker.receiver.SlashCommandReceiver
import io.olaph.slack.broker.security.VerifiesSlackSignature
import io.olaph.slack.dto.jackson.SlackCommand
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class CommandBroker constructor(val slackCommandReceivers: List<SlashCommandReceiver>) {

    @VerifiesSlackSignature
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/commands", consumes = ["application/x-www-form-urlencoded"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun receiveCommand(@Command slackCommand: SlackCommand, @RequestHeader headers: HttpHeaders) {
        slackCommandReceivers
                .filter { broker -> broker.supportsCommand(slackCommand) }
                .forEach { broker ->
                    broker.onReceiveSlashCommand(slackCommand, headers)
                }
    }
}
