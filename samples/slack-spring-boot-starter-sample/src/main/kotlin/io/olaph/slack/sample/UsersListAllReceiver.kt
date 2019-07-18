package io.olaph.slack.sample

import io.olaph.slack.broker.receiver.SlashCommandReceiver
import io.olaph.slack.broker.store.Team
import io.olaph.slack.client.SlackClient
import io.olaph.slack.dto.jackson.SlackCommand
import io.olaph.slack.dto.jackson.group.users.SlackUserListRequest
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