package io.olaph.slack.broker.receiver

import io.olaph.slack.broker.store.Team
import io.olaph.slack.client.SlackClient
import io.olaph.slack.dto.jackson.SlackCommand
import io.olaph.slack.dto.jackson.group.chat.SlackPostEphemeralRequest
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders

/**
 * Command Receiver that is invoked when no other commands are matching
 */
interface MismatchCommandReciever {

    fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team)

}


class CommandNotFoundReceiver(private val slackClient: SlackClient, private val text: String) : MismatchCommandReciever {

    companion object {
        private val LOG = LoggerFactory.getLogger(CommandNotFoundReceiver::class.java)
    }

    override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
        this.slackClient.chat()
                .postEphemeral(team.bot.accessToken)
                .with(SlackPostEphemeralRequest(
                        channel = slackCommand.channelId,
                        text = text,
                        user = slackCommand.userId))
                .onFailure {
                    LOG.error("Error while sending info message: {}", it)
                }
                .onSuccess {
                    if (LOG.isDebugEnabled)
                        LOG.debug("Sending command not found message was successful: {}", it)
                }.invoke()


    }

}
