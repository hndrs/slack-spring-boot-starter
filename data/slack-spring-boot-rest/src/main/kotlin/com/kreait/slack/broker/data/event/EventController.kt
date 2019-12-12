package com.kreait.slack.broker.data.event

import com.kreait.slack.api.contract.jackson.EventRequest
import com.kreait.slack.broker.data.DataController
import com.kreait.slack.broker.store.event.EventStore
import org.springframework.web.bind.annotation.GetMapping


@DataController
class EventController(private val eventStore: EventStore) {

    @GetMapping(path = ["/events"])
    fun events(): List<EventRequest> {
        return eventStore.findAll()
    }
}
