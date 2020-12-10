package com.kreait.slack.sample.rock_paper_scissors

import com.kreait.slack.api.SlackClient
import com.kreait.slack.api.contract.jackson.SlackCommand
import com.kreait.slack.api.contract.jackson.group.chat.PostEphemeralRequest
import com.kreait.slack.broker.receiver.SlashCommandReceiver
import com.kreait.slack.broker.store.team.Team
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component


@Component
class RockPaperScissorsCommandReceiver @Autowired constructor(private val slackClient: SlackClient) :
    SlashCommandReceiver {

    override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
        this.slackClient.chat().postEphemeral(team.bot.accessToken)
            .with(
                PostEphemeralRequest(
                    "Choose your weapon",
                    user = slackCommand.userId,
                    blocks = RPSGameHandler.blocks,
                    channel = slackCommand.channelId
                )
            )
            .onSuccess {
                println(it)
            }
            .onFailure {
                println(it)
            }.invoke()
    }

    override fun supportsCommand(slackCommand: SlackCommand): Boolean {
        return slackCommand.command.startsWith("/rock-paper-scissors")
    }

    companion object {
        const val RPS_BLOCK_ID = "RPS_BLOCK"
    }
}
