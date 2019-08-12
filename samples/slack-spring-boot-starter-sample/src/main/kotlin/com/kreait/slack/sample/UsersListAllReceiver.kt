package com.kreait.slack.sample

import com.kreait.slack.api.SlackClient
import com.kreait.slack.api.contract.jackson.SlackCommand
import com.kreait.slack.api.contract.jackson.group.users.SlackUserListRequest
import com.kreait.slack.broker.receiver.SlashCommandReceiver
import com.kreait.slack.broker.store.Team
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service


@Service
class UsersListAllReceiver(@Autowired private val slackClient: SlackClient) : SlashCommandReceiver {
    override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
        this.slackClient.users().list(team.bot.accessToken)
                .with(
                        SlackUserListRequest(true, 10, false, ""))
                .onSuccess {
                    println(it)
                }.onFailure {
                    println(it)
                }.invoke()
    }

    override fun supportsCommand(slackCommand: SlackCommand): Boolean {
        return slackCommand.command.startsWith("/listall")
    }
}
