package io.olaph.slack.broker.store

import io.olaph.slack.dto.jackson.EventRequest
import io.olaph.slack.dto.jackson.SlackEvent

class InMemoryEventStore(private val events: MutableMap<String, SlackEvent> = mutableMapOf()) : EventStore {

    override fun put(event: EventRequest) {
        if (event is SlackEvent)
            events[event.eventId] = event
    }

    override fun exists(id: String): Boolean {
        return events[id] != null
    }
}
