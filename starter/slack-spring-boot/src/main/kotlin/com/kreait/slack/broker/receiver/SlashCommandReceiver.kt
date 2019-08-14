package com.kreait.slack.broker.receiver

import com.kreait.slack.api.contract.jackson.SlackCommand
import com.kreait.slack.broker.store.Team
import org.springframework.core.Ordered
import org.springframework.http.HttpHeaders

interface SlashCommandReceiver {

    fun supportsCommand(slackCommand: SlackCommand): Boolean = true

    fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team)

    fun shouldThrowException(exception: Exception): Boolean = false

    fun order(): Int = Ordered.HIGHEST_PRECEDENCE
}
