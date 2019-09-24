package com.kreait.slack.api.spring.group.users

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test

internal class SpringUserMethodGroupTest {

    @Test
    fun info() {
        assertDoesNotThrow { SpringUserMethodGroup().info("") }
    }

    @Test
    fun list() {
        assertDoesNotThrow { SpringUserMethodGroup().list("") }
    }

    @Test
    fun listAll() {
        assertDoesNotThrow { SpringUserMethodGroup().listAll("") }
    }

    @Test
    fun conversations() {
        assertDoesNotThrow { SpringUserMethodGroup().conversations("") }
    }

    @Test
    fun deletePhoto() {
        assertDoesNotThrow { SpringUserMethodGroup().deletePhoto("") }
    }

    @Test
    fun identity() {
        assertDoesNotThrow { SpringUserMethodGroup().identity("") }
    }

    @Test
    fun setPresence() {
        assertDoesNotThrow { SpringUserMethodGroup().setPresence("") }
    }

    @Test
    fun getProfile() {
        assertDoesNotThrow { SpringUserMethodGroup().getProfile("") }
    }

    @Test
    fun setProfile() {
        assertDoesNotThrow { SpringUserMethodGroup().setProfile("") }
    }

    @Test
    fun setPhoto() {
        assertDoesNotThrow { SpringUserMethodGroup().setPhoto("") }
    }

    @Test
    fun getPresence() {
        assertDoesNotThrow { SpringUserMethodGroup().getPresence("") }
    }

    @Test
    fun lookupByEmail() {
        assertDoesNotThrow { SpringUserMethodGroup().lookupByEmail("") }
    }
}
