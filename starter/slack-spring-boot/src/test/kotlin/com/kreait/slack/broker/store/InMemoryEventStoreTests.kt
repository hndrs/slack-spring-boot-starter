package com.kreait.slack.broker.store

import com.kreait.slack.api.contract.jackson.SlackEvent
import com.kreait.slack.api.contract.jackson.sample
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

