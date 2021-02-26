package io.hndrs.slack.sample.send_button

import io.hndrs.slack.api.SlackClient
import io.hndrs.slack.api.contract.jackson.InteractiveMessage
import io.hndrs.slack.api.contract.jackson.group.chat.PostEphemeralRequest
import io.hndrs.slack.broker.receiver.InteractiveComponentReceiver
import io.hndrs.slack.broker.store.team.Team
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service

@Service
class SendButtonComponentReceiver @Autowired constructor(private val slackClient: io.hndrs.slack.api.SlackClient) :
    InteractiveComponentReceiver<InteractiveMessage> {

    override fun onReceiveInteractiveMessage(
        interactiveComponentResponse: InteractiveMessage,
        headers: HttpHeaders,
        team: Team
    ) {
        slackClient.chat().postEphemeral(team.bot.accessToken)
            .with(
                PostEphemeralRequest(
                    text = "alright", user = interactiveComponentResponse.user.id,
                    channel = interactiveComponentResponse.channel.id
                )
            )
            .onSuccess { println(it) }
            .onFailure { println(it) }
            .invoke()
    }

    override fun supportsInteractiveMessage(interactiveComponentResponse: InteractiveMessage): Boolean {
        println(interactiveComponentResponse)
        println(
            interactiveComponentResponse.callbackId == SendButtonCommandReceiver.CALLBACK_ID &&
                    interactiveComponentResponse.actions!![0].name == SendButtonCommandReceiver.ACTION_NAME
        )

        return interactiveComponentResponse.callbackId == SendButtonCommandReceiver.CALLBACK_ID &&
                interactiveComponentResponse.actions!![0].name == SendButtonCommandReceiver.ACTION_NAME
    }
}
