package com.kreait.slack.api.spring.group.reminders

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test

internal class SpringRemindersMethodGroupTest {

    @Test
    fun delete() {
        assertDoesNotThrow { SpringRemindersMethodGroup().delete("") }
    }

    @Test
    fun list() {
        assertDoesNotThrow { SpringRemindersMethodGroup().list("") }
    }

    @Test
    fun info() {
        assertDoesNotThrow { SpringRemindersMethodGroup().info("") }
    }

    @Test
    fun complete() {
        assertDoesNotThrow { SpringRemindersMethodGroup().complete("") }
    }

    @Test
    fun add() {
        assertDoesNotThrow { SpringRemindersMethodGroup().add("") }
    }
}
