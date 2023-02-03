package io.hndrs.slack.broker.receiver

import com.slack.api.methods.MethodsClient
import io.hndrs.slack.broker.command.SlashCommand
import io.hndrs.slack.broker.store.team.Team
import org.springframework.http.HttpHeaders

/**
 * Command Receiver that is invoked when no other commands are matching
 */
interface MismatchCommandReceiver {
    /**
     * MismatchReceiver that responds with a default error message when no command was found
     *
     * @param slashCommand the received slack-command
     * @param team the team according to that slash-command
     */
    fun onMismatchedSlashCommand(slashCommand: SlashCommand, headers: HttpHeaders, team: Team, methods: MethodsClient)
}

/**
 * The Receiver that is invoked when an unknown command was entered
 */
class CommandNotFoundReceiver(private val text: String) :
    MismatchCommandReceiver {

    override fun onMismatchedSlashCommand(
        slashCommand: SlashCommand,
        headers: HttpHeaders,
        team: Team,
        methods: MethodsClient
    ) {
        methods.chatPostEphemeral {
            it
                .channel(slashCommand.channelId)
                .user(slashCommand.userId)
                .text(text)
        }
    }
}
