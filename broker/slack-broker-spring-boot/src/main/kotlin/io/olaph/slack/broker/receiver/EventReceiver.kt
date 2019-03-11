package io.olaph.slack.broker.receiver

import io.olaph.slack.dto.jackson.SlackEvent
import org.springframework.http.HttpHeaders

interface EventReceiver {

    fun supportsEvent(slackEvent: SlackEvent): Boolean = true

    fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders)
}
