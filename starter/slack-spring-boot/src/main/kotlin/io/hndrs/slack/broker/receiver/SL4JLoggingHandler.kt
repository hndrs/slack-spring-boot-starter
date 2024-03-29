package io.hndrs.slack.broker.receiver

import io.hndrs.slack.broker.command.CommandHandler
import io.hndrs.slack.broker.command.SlashCommand
import io.hndrs.slack.broker.event.EventHandler
import io.hndrs.slack.broker.event.SlackEvent
import io.hndrs.slack.broker.installation.InstallationHandler
import io.hndrs.slack.broker.store.team.Team
import org.slf4j.LoggerFactory
import org.springframework.core.Ordered
import org.springframework.http.HttpHeaders

class SL4JLoggingHandler : EventHandler, CommandHandler, InstallationHandler {
    /**
     * receivers will be sorted ascending by this order
     */
    override fun order(): Int = Ordered.HIGHEST_PRECEDENCE

    override fun shouldThrowException(exception: Exception): Boolean = false

    override fun onInstallation(team: Team) {
        LOG.info("Received installation request by {}", team.teamName)
    }

    override fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team) {
        LOG.info("Received SlackCallBack    {}\nHeaders:  {}", slackEvent, headers)
    }

    override fun onSlashCommand(slashCommand: SlashCommand, headers: HttpHeaders, team: Team) {
        LOG.info("Received SlackCommand:    {}\nHeaders:    {}", slashCommand, headers)
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(SL4JLoggingHandler::class.java)!!
    }
}
