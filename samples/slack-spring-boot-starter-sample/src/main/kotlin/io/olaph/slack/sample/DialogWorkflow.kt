package io.olaph.slack.sample

import io.olaph.slack.broker.receiver.InteractiveComponentReceiver
import io.olaph.slack.broker.receiver.SlashCommandReceiver
import io.olaph.slack.broker.store.Team
import io.olaph.slack.client.SlackClient
import io.olaph.slack.dto.jackson.InteractiveComponentResponse
import io.olaph.slack.dto.jackson.SlackCommand
import io.olaph.slack.dto.jackson.group.chat.SlackPostMessageRequest
import io.olaph.slack.dto.jackson.group.dialog.Dialog
import io.olaph.slack.dto.jackson.group.dialog.SlackOpenDialogRequest
import io.olaph.slack.dto.jackson.group.dialog.TextElement
import io.olaph.slack.dto.jackson.group.dialog.Type
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component

@Component
class DialogSubmissionReceiver @Autowired constructor(private val slackClient: SlackClient) : InteractiveComponentReceiver {

    companion object {
        const val CALL_BACK_ID = "unique_callback_id"
    }

    override fun supportsInteractiveMessage(interactiveComponentResponse: InteractiveComponentResponse): Boolean {
        return interactiveComponentResponse.callbackId == CALL_BACK_ID
    }

    override fun onReceiveInteractiveMessage(interactiveComponentResponse: InteractiveComponentResponse, headers: HttpHeaders, team: Team) {

        this.slackClient.chat().postMessage(team.bot.accessToken)
                .with(SlackPostMessageRequest(
                        text = "We received your submission carry on :)",
                        channel = interactiveComponentResponse.channel.id
                )).invoke()
    }

}

@Component
class OpenDialogCommandReceiver @Autowired constructor(private val slackClient: SlackClient) : SlashCommandReceiver {

    override fun supportsCommand(slackCommand: SlackCommand): Boolean {
        return slackCommand.command.startsWith("/open-dialog")
    }

    override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
        val openDialogRequest = SlackOpenDialogRequest(
                trigger_id = slackCommand.triggerId,
                dialog = Dialog(
                        callback_id = DialogSubmissionReceiver.CALL_BACK_ID,
                        title = "Welcome Please Entery your name",
                        elements = listOf(
                                TextElement(
                                        label = "Name",
                                        name = "name",
                                        type = Type.TEXT,
                                        placeholder = "Your name"
                                )
                        )
                ))

        this.slackClient.dialog().open(team.bot.accessToken)
                .with(openDialogRequest)
                .invoke()
    }

}
