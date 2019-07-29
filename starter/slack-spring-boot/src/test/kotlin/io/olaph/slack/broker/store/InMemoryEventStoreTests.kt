package io.olaph.slack.broker.store

import io.olaph.slack.dto.jackson.SlackEvent
import io.olaph.slack.dto.jackson.sample
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
 
