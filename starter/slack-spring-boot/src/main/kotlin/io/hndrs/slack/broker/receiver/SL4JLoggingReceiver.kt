package io.hndrs.slack.broker.receiver

import io.hndrs.slack.api.contract.jackson.event.SlackEvent
import io.hndrs.slack.broker.command.SlackCommand
import io.hndrs.slack.broker.store.team.Team
import org.slf4j.LoggerFactory
import org.springframework.core.Ordered
import org.springframework.http.HttpHeaders

class SL4JLoggingReceiver : EventReceiver, SlashCommandReceiver, InstallationReceiver {
    /**
     * receivers will be sorted ascending by this order
     */
    override fun order(): Int = Ordered.HIGHEST_PRECEDENCE

    override fun shouldThrowException(exception: Exception): Boolean = false

    override fun onReceiveInstallation(code: String, state: String, team: Team) {
        LOG.info("Received installation request")
    }

    override fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team) {
        LOG.info("Received SlackCallBack    {}\nHeaders:  {}", slackEvent, headers)
    }

    override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
        LOG.info("Received SlackCommand:    {}\nHeaders:    {}", slackCommand, headers)
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(SL4JLoggingReceiver::class.java)!!
    }
}
