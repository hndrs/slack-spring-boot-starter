package io.olaph.slack.sample

import io.olaph.slack.broker.receiver.SlashCommandReceiver
import io.olaph.slack.broker.store.Team
import io.olaph.slack.client.SlackClient
import io.olaph.slack.dto.jackson.SlackCommand
import io.olaph.slack.dto.jackson.group.respond.ResponseType
import io.olaph.slack.dto.jackson.group.respond.SlackRespondMessageRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component

@Component
class ResponseCommandReceiver @Autowired constructor(private val slackClient: SlackClient) : SlashCommandReceiver {
    override fun supportsCommand(slackCommand: SlackCommand): Boolean {
        return slackCommand.command.startsWith("/response")
    }

    override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
        this.slackClient.respond().ephemeral(slackCommand.responseUrl)
                .with(SlackRespondMessageRequest(text = "lol", responseType = ResponseType.EPHEMERAL))
                .invoke()
    }
}
