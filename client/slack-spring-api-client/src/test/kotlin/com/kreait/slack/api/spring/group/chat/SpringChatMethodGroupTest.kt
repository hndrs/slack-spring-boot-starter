package com.kreait.slack.api.spring.group.chat

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test

internal class SpringChatMethodGroupTest {

    @Test
    fun getPermalink() {
        assertDoesNotThrow { SpringChatMethodGroup().getPermalink("") }
    }

    @Test
    fun meMessage() {
        assertDoesNotThrow { SpringChatMethodGroup().meMessage("") }
    }

    @Test
    fun unfurl() {
        assertDoesNotThrow { SpringChatMethodGroup().unfurl("") }
    }

    @Test
    fun delete() {
        assertDoesNotThrow { SpringChatMethodGroup().delete("") }
    }

    @Test
    fun postMessage() {
        assertDoesNotThrow { SpringChatMethodGroup().postMessage("") }
    }

    @Test
    fun postEphemeral() {
        assertDoesNotThrow { SpringChatMethodGroup().postEphemeral("") }
    }

    @Test
    fun update() {
        assertDoesNotThrow { SpringChatMethodGroup().update("") }
    }
}
