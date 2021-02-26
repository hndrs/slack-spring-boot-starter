package io.hndrs.slack.broker.receiver

import io.hndrs.slack.api.SlackClient
import io.hndrs.slack.api.contract.jackson.SlackCommand
import io.hndrs.slack.api.contract.jackson.group.chat.PostEphemeralRequest
import io.hndrs.slack.broker.store.team.Team
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders

/**
 * Command Receiver that is invoked when no other commands are matching
 */
interface MismatchCommandReceiver {
    /**
     * MismatchReceiver that responds with a default error message when no command was found
     *
     * @param slackCommand the received slack-command
     * @param team the team according to that slash-command
     */
    fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team)

}

/**
 * The Receiver that is invoked when an unknown command was entered
 */
class CommandNotFoundReceiver(private val slackClient: io.hndrs.slack.api.SlackClient, private val text: String) :
    MismatchCommandReceiver {

    override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
        this.slackClient.chat()
            .postEphemeral(team.bot.accessToken)
            .with(
                PostEphemeralRequest(
                    channel = slackCommand.channelId,
                    text = text,
                    user = slackCommand.userId
                )
            )
            .onFailure {
                LOG.error("Error while sending info message: {}", it)
            }
            .onSuccess {
                if (LOG.isDebugEnabled)
                    LOG.debug("Sending command not found message was successful: {}", it)
            }.invoke()


    }

    companion object {
        private val LOG = LoggerFactory.getLogger(CommandNotFoundReceiver::class.java)
    }
}
