package com.kreait.slack.broker.receiver

import com.kreait.slack.api.contract.jackson.InteractiveComponentResponse
import com.kreait.slack.api.contract.jackson.SlackCommand
import com.kreait.slack.api.contract.jackson.SlackEvent
import com.kreait.slack.broker.store.Team
import org.slf4j.LoggerFactory
import org.springframework.core.Ordered
import org.springframework.http.HttpHeaders

class SL4JLoggingReceiver : EventReceiver, SlashCommandReceiver, InteractiveComponentReceiver<InteractiveComponentResponse>, InstallationReceiver {
    override fun order(): Int = Ordered.HIGHEST_PRECEDENCE

    override fun shouldThrowException(exception: Exception): Boolean = false

    override fun onReceiveInstallation(code: String, state: String, team: Team) {
        LOG.info("Received installation request")
    }

    companion object {
        val LOG = LoggerFactory.getLogger(SL4JLoggingReceiver::class.java)!!
    }

    override fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team) {
        LOG.info("Received SlackCallBack    {}\nHeaders:  {}", slackEvent, headers)
    }

    override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
        LOG.info("Received SlackCommand:    {}\nHeaders:    {}", slackCommand, headers)
    }

    override fun onReceiveInteractiveMessage(interactiveComponentResponse: InteractiveComponentResponse, headers: HttpHeaders, team: Team) {
        LOG.info("Received Interactive Component:   {}\nHeaders:    {}", interactiveComponentResponse, headers)
    }
}
