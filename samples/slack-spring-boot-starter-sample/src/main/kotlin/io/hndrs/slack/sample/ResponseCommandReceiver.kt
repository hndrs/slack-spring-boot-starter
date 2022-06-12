package io.hndrs.slack.sample

import com.slack.api.Slack
import io.hndrs.slack.broker.command.SlashCommand
import io.hndrs.slack.broker.receiver.SlashCommandReceiver
import io.hndrs.slack.broker.store.team.Team
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component

@Component
class ResponseCommandReceiver @Autowired constructor(
) : SlashCommandReceiver {
    override fun supportsCommand(slashCommand: SlashCommand): Boolean {
        return slashCommand.command.startsWith("/response")
    }

    override fun onSlashCommand(slashCommand: SlashCommand, headers: HttpHeaders, team: Team) {
        Slack.getInstance().methods(team.accessToken, team.teamId)
            .chatPostEphemeral {
                it.user(slashCommand.userId)
                    .channel(slashCommand.channelId)
                    .text("works")
            }
    }
}
