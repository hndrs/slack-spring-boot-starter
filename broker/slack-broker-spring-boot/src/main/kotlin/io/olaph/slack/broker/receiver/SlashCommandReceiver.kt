package io.olaph.slack.broker.receiver

import io.olaph.slack.dto.jackson.SlackCommand
import org.springframework.http.HttpHeaders

interface SlashCommandReceiver {

    fun supportsCommand(slackCommand: SlackCommand): Boolean = true

    fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders)
}
