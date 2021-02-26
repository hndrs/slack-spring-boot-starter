package io.hndrs.slack.sample

import io.hndrs.slack.api.SlackClient
import io.hndrs.slack.api.contract.jackson.InteractiveMessage
import io.hndrs.slack.api.contract.jackson.SlackCommand
import io.hndrs.slack.api.contract.jackson.group.chat.PostMessageRequest
import io.hndrs.slack.api.contract.jackson.group.dialog.Dialog
import io.hndrs.slack.api.contract.jackson.group.dialog.OpenDialogRequest
import io.hndrs.slack.api.contract.jackson.group.dialog.TextElement
import io.hndrs.slack.api.contract.jackson.group.dialog.Type
import io.hndrs.slack.broker.receiver.InteractiveComponentReceiver
import io.hndrs.slack.broker.receiver.SlashCommandReceiver
import io.hndrs.slack.broker.store.team.Team
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component

@Component
class DialogSubmissionReceiver @Autowired constructor(private val slackClient: io.hndrs.slack.api.SlackClient) :
    InteractiveComponentReceiver<InteractiveMessage> {

    override fun supportsInteractiveMessage(interactiveComponentResponse: InteractiveMessage): Boolean {
        return interactiveComponentResponse.callbackId == CALL_BACK_ID
    }

    override fun onReceiveInteractiveMessage(
        interactiveComponentResponse: InteractiveMessage,
        headers: HttpHeaders,
        team: Team
    ) {

        this.slackClient.chat().postMessage(team.bot.accessToken)
            .with(
                PostMessageRequest(
                    text = "We received your submission carry on :)",
                    channel = interactiveComponentResponse.channel.id
                )
            ).invoke()
    }

    companion object {
        const val CALL_BACK_ID = "unique_callback_id"
    }
}

@Component
class OpenDialogCommandReceiver @Autowired constructor(private val slackClient: io.hndrs.slack.api.SlackClient) : SlashCommandReceiver {

    override fun supportsCommand(slackCommand: SlackCommand): Boolean {
        return slackCommand.command.startsWith("/open-dialog")
    }

    override fun onReceiveSlashCommand(slackCommand: SlackCommand, headers: HttpHeaders, team: Team) {
        val openDialogRequest = OpenDialogRequest(
            trigger_id = slackCommand.triggerId,
            dialog = Dialog(
                callback_id = DialogSubmissionReceiver.CALL_BACK_ID,
                title = "Welcome Please enter your name",
                elements = listOf(
                    TextElement(
                        label = "Name",
                        name = "name",
                        type = Type.TEXT,
                        placeholder = "Your name"
                    )
                )
            )
        )

        this.slackClient.dialog().open(team.bot.accessToken)
            .with(openDialogRequest)
            .invoke()
    }

}
