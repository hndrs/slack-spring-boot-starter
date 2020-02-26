package com.kreait.slack.broker.receiver

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.kreait.slack.api.contract.jackson.event.Event
import com.kreait.slack.api.contract.jackson.event.SlackEvent
import com.kreait.slack.broker.store.team.Team
import org.springframework.http.HttpHeaders

/**
 * EventReceiver that should be implemented to react to incoming events
 */
abstract class TypedEventReceiver<T : Event>(private val eventType: Class<T>) : EventReceiver {

    private val mapper = jacksonObjectMapper()

    final override fun supportsEvent(slackEvent: SlackEvent): Boolean {
        return this.supportsEvent(slackEvent, resolveMappedObject(slackEvent))
    }

    final override fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team) {
        this.onReceiveEvent(slackEvent, resolveMappedObject(slackEvent), headers, team)
    }

    open fun supportsEvent(slackEvent: SlackEvent, event: T): Boolean {
        return event.slackTypeString() == slackEvent.type
    }

    abstract fun onReceiveEvent(slackEvent: SlackEvent, event: T, headers: HttpHeaders, team: Team)

    /**
     * Writes [SlackEvent] event payload as a json string to then map it to given object
     */
    private fun resolveMappedObject(slackEvent: SlackEvent): T {
        val json = mapper.writeValueAsString(slackEvent.event)
        return mapper.readValue(json, eventType)
    }
}
