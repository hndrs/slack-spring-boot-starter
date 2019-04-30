package io.olaph.slack.sample

import io.olaph.slack.broker.receiver.SlashCommandReceiver
import io.olaph.slack.broker.store.Team
import io.olaph.slack.dto.jackson.SlackCommand
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component

@Component
class ErrorCommandReceiver : SlashCommandReceiver {

    override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
        throw IllegalArgumentException()
    }
}
