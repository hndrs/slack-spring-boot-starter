package io.olaph.slack.sample

import io.olaph.slack.broker.receiver.EventReceiver
import io.olaph.slack.broker.store.Team
import io.olaph.slack.client.SlackClient
import io.olaph.slack.dto.jackson.SlackEvent
import io.olaph.slack.dto.jackson.group.chat.SlackPostMessageRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component

@Component
class ChannelRenamedEventReceiver @Autowired constructor(private val slackClient: SlackClient) : EventReceiver {

    override fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team) {

        val channelId = (slackEvent.event["channel"] as Map<*, *>)["id"] as String
        val newChannelName = (slackEvent.event["channel"] as Map<*, *>)["name"] as String

        this.slackClient.chat().postMessage(team.bot.accessToken)
                .with(SlackPostMessageRequest(
                        text = "Wow $newChannelName is really cool",
                        channel = channelId
                )).invoke()
    }

    override fun supportsEvent(slackEvent: SlackEvent): Boolean {
        return slackEvent.event["type"] == "channel_rename"
    }
}
