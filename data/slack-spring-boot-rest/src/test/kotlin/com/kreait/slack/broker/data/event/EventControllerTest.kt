package com.kreait.slack.broker.data.event

import com.kreait.slack.api.contract.jackson.event.EventRequest
import com.kreait.slack.broker.store.event.InMemoryEventStore
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

internal class EventControllerTest{

    @DisplayName("/events")
    fun events(){
        val eventStore = InMemoryEventStore()
        eventStore.put(EventRequest.sample)
        EventController()
    }
}
