package com.kreait.slack.broker.store.event

import com.kreait.slack.api.contract.jackson.EventRequest
import com.kreait.slack.api.contract.jackson.SlackEvent

class InMemoryEventStore(private val events: MutableMap<String, SlackEvent> = mutableMapOf()) : EventStore {

    override fun put(event: EventRequest) {
        if (event is SlackEvent)
            events[event.eventId] = event
    }

    override fun exists(id: String): Boolean {
        return events[id] != null
    }
}
