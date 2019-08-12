package com.kreait.slack.broker.receiver

import com.kreait.slack.api.contract.jackson.SlackEvent
import com.kreait.slack.broker.store.Team
import org.springframework.http.HttpHeaders

interface EventReceiver {

    fun supportsEvent(slackEvent: SlackEvent): Boolean = true

    fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team)
}
