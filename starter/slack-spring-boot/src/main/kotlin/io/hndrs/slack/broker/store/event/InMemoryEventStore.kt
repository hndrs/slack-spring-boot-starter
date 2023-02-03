package io.hndrs.slack.broker.store.event

import io.hndrs.slack.broker.event.EventRequest
import io.hndrs.slack.broker.event.SlackEvent

/**
 * Default implementation for the [EventStore]
 *
 * @property events
 */
class InMemoryEventStore(private val events: MutableMap<String, SlackEvent> = mutableMapOf()) : EventStore {

    override fun put(event: EventRequest) {
        if (event is SlackEvent) {
            events[event.eventId] = event
        }
    }

    override fun exists(id: String): Boolean {
        return events[id] != null
    }
}
