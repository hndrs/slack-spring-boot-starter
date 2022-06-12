package io.hndrs.slack.sample

import com.slack.api.Slack
import com.slack.api.model.event.MessageEvent
import io.hndrs.slack.broker.receiver.TypedEventReceiver
import io.hndrs.slack.broker.store.team.Team
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component


@Component
class MessageReceiver : TypedEventReceiver<MessageEvent>(MessageEvent::class) {
    override fun onReceiveEvent(event: MessageEvent, headers: HttpHeaders, team: Team) {
        Slack.getInstance()
            .methods(team.accessToken, team.teamId)
            .chatPostMessage {
                it.channel(event.channel)
                    .text("DONT SAY THAT")
            }
    }

    override fun supports(event: MessageEvent): Boolean {
        return event.botId == null
    }
}
