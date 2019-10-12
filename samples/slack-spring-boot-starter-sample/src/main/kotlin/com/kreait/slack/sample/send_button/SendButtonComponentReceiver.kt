package com.kreait.slack.sample.send_button

import com.kreait.slack.api.SlackClient
import com.kreait.slack.api.contract.jackson.InteractiveMessage
import com.kreait.slack.api.contract.jackson.group.chat.PostEphemeralRequest
import com.kreait.slack.broker.receiver.InteractiveComponentReceiver
import com.kreait.slack.broker.store.team.Team
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service

@Service
class SendButtonComponentReceiver @Autowired constructor(private val slackClient: SlackClient) : InteractiveComponentReceiver<InteractiveMessage> {

    override fun onReceiveInteractiveMessage(interactiveComponentResponse: InteractiveMessage, headers: HttpHeaders, team: Team) {
        slackClient.chat().postEphemeral(team.bot.accessToken)
                .with(PostEphemeralRequest(text = "alright", user = interactiveComponentResponse.user.id,
                        channel = interactiveComponentResponse.channel.id))
                .onSuccess { println(it) }
                .onFailure { println(it) }
                .invoke()
    }

    override fun supportsInteractiveMessage(interactiveComponentResponse: InteractiveMessage): Boolean {
        println(interactiveComponentResponse)
        println(interactiveComponentResponse.callbackId == SendButtonCommandReceiver.CALLBACK_ID &&
                interactiveComponentResponse.actions!![0].name == SendButtonCommandReceiver.ACTION_NAME)

        return interactiveComponentResponse.callbackId == SendButtonCommandReceiver.CALLBACK_ID &&
                interactiveComponentResponse.actions!![0].name == SendButtonCommandReceiver.ACTION_NAME
    }
}
