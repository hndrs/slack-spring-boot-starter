package io.hndrs.slack.broker.command

import com.slack.api.Slack
import io.hndrs.slack.broker.store.team.Team
import org.springframework.http.HttpHeaders

/**
 * Command Receiver that is invoked when no other commands are matching
 */
interface UnknownCommandHandler {
    /**
     * MismatchReceiver that responds with a default error message when no command was found
     *
     * @param slashCommand the received slack-command
     * @param team the team according to that slash-command
     */
    fun onUnknownCommand(slashCommand: SlashCommand, headers: HttpHeaders, team: Team)
}

/**
 * The Receiver that is invoked when an unknown command was entered
 */
class DefaultUnknownCommandHandler(
    private val slack: Slack,
    private val text: String,
) : UnknownCommandHandler {

    override fun onUnknownCommand(
        slashCommand: SlashCommand,
        headers: HttpHeaders,
        team: Team,
    ) {
        slack.methods(team.accessToken).chatPostEphemeral {
            it
                .channel(slashCommand.channelId)
                .user(slashCommand.userId)
                .text(text)
        }
    }
}
