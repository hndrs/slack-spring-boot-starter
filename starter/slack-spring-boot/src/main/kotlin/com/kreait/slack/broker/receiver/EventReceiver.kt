package com.kreait.slack.broker.receiver

import com.kreait.slack.api.contract.jackson.SlackEvent
import com.kreait.slack.broker.store.Team
import org.springframework.core.Ordered
import org.springframework.http.HttpHeaders

interface EventReceiver {

    fun supportsEvent(slackEvent: SlackEvent): Boolean = true

    fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team)

    fun shouldThrowException(exception: Exception): Boolean = false

    /**
     * receivers will be sorted ascending by this order
     */
    fun order(): Int = Ordered.HIGHEST_PRECEDENCE
}
