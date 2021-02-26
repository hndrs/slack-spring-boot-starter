package io.hndrs.slack.broker.receiver

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.hndrs.slack.api.contract.jackson.event.Event
import io.hndrs.slack.api.contract.jackson.event.SlackEvent
import io.hndrs.slack.broker.store.team.Team
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

    /**
     * Should return true if the receiver supports the incoming event.
     * This default implementation returns true when the generic [eventType] has the same type
     * as the incoming [slackEvent]
     *
     * [slackEvent] is the original raw event
     * [event] payload parsed to the
     */
    open fun supportsEvent(slackEvent: SlackEvent, event: T): Boolean {
        return event.slackTypeString() == slackEvent.type
    }

    /**
     * Implementation that provides the original [slackEvent] and the deserialized [event]
     */
    abstract fun onReceiveEvent(slackEvent: SlackEvent, event: T, headers: HttpHeaders, team: Team)

    /**
     * Writes [SlackEvent] event payload as a json string to then map it to given object
     */
    private fun resolveMappedObject(slackEvent: SlackEvent): T {
        val json = mapper.writeValueAsString(slackEvent.event)
        return mapper.readValue(json, eventType)
    }
}
