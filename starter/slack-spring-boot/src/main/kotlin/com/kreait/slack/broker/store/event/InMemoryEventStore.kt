package com.kreait.slack.broker.store.event

import com.kreait.slack.api.contract.jackson.event.EventRequest
import com.kreait.slack.api.contract.jackson.event.SlackEvent

/**
 * Default implementation for the [EventStore]
 *
 * @property events
 */
class InMemoryEventStore(private val events: MutableMap<String, SlackEvent> = mutableMapOf()) : EventStore {

    override fun put(event: EventRequest) {
        if (event is SlackEvent)
            events[event.eventId] = event
    }

    override fun findAll(): List<EventRequest> {
        return events.entries.map { it.value }
    }

    override fun exists(id: String): Boolean {
        return events[id] != null
    }
}
