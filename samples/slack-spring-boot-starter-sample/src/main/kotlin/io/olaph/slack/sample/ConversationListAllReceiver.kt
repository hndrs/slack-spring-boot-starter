package io.olaph.slack.sample

import io.olaph.slack.broker.receiver.SlashCommandReceiver
import io.olaph.slack.broker.store.Team
import io.olaph.slack.client.SlackClient
import io.olaph.slack.dto.jackson.SlackCommand
import io.olaph.slack.dto.jackson.group.conversations.ConversationsListRequest
import io.olaph.slack.dto.jackson.group.users.SlackUserConversationListRequest
import io.olaph.slack.dto.jackson.group.users.SlackUserListRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service


@Service
class ConversationListAllReceiver(@Autowired private val slackClient: SlackClient) : SlashCommandReceiver {
    override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
        this.slackClient.conversation().list(team.bot.accessToken)
                .with(
                      ConversationsListRequest()
                )
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
