package com.kreait.slack.broker.store

import com.kreait.slack.broker.extensions.sample
import com.kreait.slack.broker.store.team.InMemoryTeamStore
import com.kreait.slack.broker.store.team.Team
import com.kreait.slack.broker.store.team.TeamNotFoundException
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("InMemoryTeamStore")
class InMemoryTeamStoreTests {


    @DisplayName("Test Add and Remove")
    @Test
    fun addAndRemoveTeam() {

        val inMemoryTeamStore = InMemoryTeamStore()
        inMemoryTeamStore.put(Team.sample().copy(teamId = "TestTeamId"))

        assertEquals(Team.sample().copy(teamId = "TestTeamId"), inMemoryTeamStore.findById("TestTeamId"))

        inMemoryTeamStore.removeById("TestTeamId")
        assertThrows(TeamNotFoundException::class.java) { inMemoryTeamStore.findById("TestTeamId") }

    }

    @DisplayName("Remove Non Existent")
    @Test
    fun removeNonExistent() {

        val inMemoryTeamStore = InMemoryTeamStore()
        assertDoesNotThrow { inMemoryTeamStore.removeById("NonExistentTeamId") }

    }
}
