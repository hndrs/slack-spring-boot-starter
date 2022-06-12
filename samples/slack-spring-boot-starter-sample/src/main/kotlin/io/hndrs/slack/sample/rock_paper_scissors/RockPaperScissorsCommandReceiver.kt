package io.hndrs.slack.sample.rock_paper_scissors

import com.slack.api.Slack
import io.hndrs.slack.broker.command.SlackCommand
import io.hndrs.slack.broker.receiver.SlashCommandReceiver
import io.hndrs.slack.broker.store.team.Team
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component


@Component
class RockPaperScissorsCommandReceiver @Autowired constructor(private val slack: Slack) :
    SlashCommandReceiver {

    override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {

        val methods = slack.methods(team.accessToken, team.teamId)

        methods.chatPostEphemeral {
            it.text("Choose your weapon")
                .user(slackCommand.userId)
                .channel(slackCommand.channelName)
        }.let {
            println(it)
            //TODO if bot user is not part of channel inform user otherwise

        }
    }

    override fun supportsCommand(slackCommand: SlackCommand): Boolean {
        return slackCommand.command.startsWith("/rock-paper-scissors")
    }

    companion object {
        const val RPS_BLOCK_ID = "RPS_BLOCK"
    }
}
