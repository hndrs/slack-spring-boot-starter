package com.kreait.slack.sample.send_button

import com.kreait.slack.api.SlackClient
import com.kreait.slack.api.contract.jackson.SlackCommand
import com.kreait.slack.api.contract.jackson.common.Action
import com.kreait.slack.api.contract.jackson.common.messaging.Attachment
import com.kreait.slack.api.contract.jackson.group.respond.RespondMessageRequest
import com.kreait.slack.api.contract.jackson.group.respond.ResponseType
import com.kreait.slack.broker.receiver.SlashCommandReceiver
import com.kreait.slack.broker.store.team.Team
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service

@Service
class SendButtonCommandReceiver @Autowired constructor(private val slackClient: SlackClient) : SlashCommandReceiver {

    companion object {
        const val CALLBACK_ID = "test_callback"
        const val ACTION_NAME = "test_action"
    }

    override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
        val attachment = listOf(Attachment(
                actions = listOf(
                        Action(name = ACTION_NAME,
                                text = "click me",
                                style = Action.Style.PRIMARY,
                                type = Action.ActionType.BUTTON)
                ), fallback = "test",
                callbackId = CALLBACK_ID,
                attachmentType = "default"))

        this.slackClient.respond().message(slackCommand.responseUrl)
                .with(RespondMessageRequest(attachments = attachment, responseType = ResponseType.EPHEMERAL))
                .onSuccess { println(it) }
                .onFailure { println(it) }
                .invoke()
    }

    override fun supportsCommand(slackCommand: SlackCommand): Boolean {
        return slackCommand.command.startsWith("/button")
    }
}