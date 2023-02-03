package io.hndrs.slack.broker.store

import io.hndrs.slack.broker.extensions.sample
import io.hndrs.slack.broker.store.user.InMemoryUserStore
import io.hndrs.slack.broker.store.user.User
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("InMemoryUserStore")
class InMemoryUserStoreTests {

    @DisplayName("Test Add and Remove")
    @Test
    fun addAndRemoveUser() {
        val inMemoryUserStore = InMemoryUserStore()
        inMemoryUserStore.put(User.sample().copy(id = "TestUserId"))

        assertEquals(User.sample().copy(id = "TestUserId"), inMemoryUserStore.findById("TestUserId"))

        inMemoryUserStore.update(User.sample().copy(id = "TestUserId", name = "test"))
        assertEquals("test", inMemoryUserStore.findById("TestUserId").name)
    }

    @DisplayName("Test Add and Remove")
    @Test
    fun findUserByTeam() {
        val inMemoryUserStore = InMemoryUserStore()
        inMemoryUserStore.put(User.sample().copy(id = "TestUserId1", teamId = "TestTeamId1"))
        inMemoryUserStore.put(User.sample().copy(id = "TestUserId2", teamId = "TestTeamId2"))

        assertEquals(1, inMemoryUserStore.findByTeam("TestTeamId1").size)
    }

    @DisplayName("Update Non Existent")
    @Test
    fun updateNonExistent() {
        val inMemoryUserStore = InMemoryUserStore()
        assertDoesNotThrow { inMemoryUserStore.update(User.sample().copy("NonExistentUserId")) }
    }
}
