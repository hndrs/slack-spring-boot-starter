package com.kreait.slack.broker.receiver

import com.kreait.slack.api.contract.jackson.event.Event
import com.kreait.slack.api.contract.jackson.event.SlackEvent
import com.kreait.slack.broker.store.team.Team
import org.springframework.http.HttpHeaders

/**
 * EventReceiver that should be implemented to react to incoming events
 */
interface TypedEventReceiver<T : Event> : EventReceiver {

    override fun onReceiveEvent(slackEvent: SlackEvent<Event>, headers: HttpHeaders, team: Team) {
        this.onReceive(slackEvent as SlackEvent<T>, headers, team)
    }

    fun onReceive(slackEvent: SlackEvent<T>, headers: HttpHeaders, team: Team)
}
