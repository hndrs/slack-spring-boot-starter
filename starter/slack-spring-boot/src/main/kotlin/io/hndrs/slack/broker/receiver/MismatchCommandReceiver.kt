package io.hndrs.slack.broker.receiver

import com.slack.api.Slack
import io.hndrs.slack.broker.command.SlackCommand
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
class CommandNotFoundReceiver(private val slackClient: Slack, private val text: String) :
    MismatchCommandReceiver {

    override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
        //todo post message

    }

    companion object {
        private val LOG = LoggerFactory.getLogger(CommandNotFoundReceiver::class.java)
    }
}
