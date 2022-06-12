package io.hndrs.slack.broker.store

import io.hndrs.slack.api.contract.jackson.event.SlackEvent
import io.hndrs.slack.broker.sample
import io.hndrs.slack.broker.store.event.InMemoryEventStore
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("InMemoryEventStore")
class InMemoryEventStoreTests {


    @DisplayName("Operations")
    @Test
    fun addAndRemoveTeam() {

        val sampleId = "TestEventId"
        val inMemoryEventStore = InMemoryEventStore()
        inMemoryEventStore.put(SlackEvent.sample().copy(eventId = sampleId))

        assertTrue(inMemoryEventStore.exists(sampleId))
        assertFalse(inMemoryEventStore.exists("UnknownId"))
    }

}

