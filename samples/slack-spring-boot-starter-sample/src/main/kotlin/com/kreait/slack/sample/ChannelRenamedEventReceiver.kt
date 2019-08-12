package com.kreait.slack.sample

import com.kreait.slack.api.SlackClient
import com.kreait.slack.api.contract.jackson.SlackEvent
import com.kreait.slack.api.contract.jackson.group.chat.PostMessageRequest
import com.kreait.slack.broker.receiver.EventReceiver
import com.kreait.slack.broker.store.Team
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component

@Component
class ChannelRenamedEventReceiver @Autowired constructor(private val slackClient: SlackClient) : EventReceiver {

    override fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team) {

        val channelId = (slackEvent.event["channel"] as Map<*, *>)["id"] as String
        val newChannelName = (slackEvent.event["channel"] as Map<*, *>)["name"] as String

        this.slackClient.chat().postMessage(team.bot.accessToken)
                .with(PostMessageRequest(
                        text = "Wow $newChannelName is really cool",
                        channel = channelId
                )).invoke()
    }

    override fun supportsEvent(slackEvent: SlackEvent): Boolean {
        return slackEvent.event["type"] == "channel_rename"
    }
}
