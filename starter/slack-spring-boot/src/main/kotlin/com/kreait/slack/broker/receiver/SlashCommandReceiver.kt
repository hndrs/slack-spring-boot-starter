package com.kreait.slack.broker.receiver

import com.kreait.slack.api.contract.jackson.SlackCommand
import com.kreait.slack.broker.store.Team
import org.springframework.http.HttpHeaders

interface SlashCommandReceiver {

    fun supportsCommand(slackCommand: SlackCommand): Boolean = true

    fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team)
}
