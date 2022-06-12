package io.hndrs.slack.sample

import com.slack.api.Slack
import io.hndrs.slack.broker.command.SlackCommand
import io.hndrs.slack.broker.receiver.SlashCommandReceiver
import io.hndrs.slack.broker.store.team.Team
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component

@Component
class ResponseCommandReceiver @Autowired constructor(
) : SlashCommandReceiver {
    override fun supportsCommand(slackCommand: SlackCommand): Boolean {
        return slackCommand.command.startsWith("/response")
    }

    override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
        Slack.getInstance().methods(team.accessToken, team.teamId)
            .chatPostEphemeral {
                it.user(slackCommand.userId)
                    .channel(slackCommand.channelId)
                    .text("works")
            }
    }
}
