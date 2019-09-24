package com.kreait.slack.api.spring.group.conversations

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class SpringConversationsMethodGroupTest {

    @Test
    fun close() {
        assertDoesNotThrow { SpringConversationsMethodGroup().close("") }
    }

    @Test
    fun create() {
        assertDoesNotThrow { SpringConversationsMethodGroup().create("") }
    }

    @Test
    fun history() {
        assertDoesNotThrow { SpringConversationsMethodGroup().history("") }
    }

    @Test
    fun info() {
        assertDoesNotThrow { SpringConversationsMethodGroup().info("") }
    }

    @Test
    fun invite() {
        assertDoesNotThrow { SpringConversationsMethodGroup().invite("") }
    }

    @Test
    fun join() {
        assertDoesNotThrow { SpringConversationsMethodGroup().join("") }
    }

    @Test
    fun kick() {
        assertDoesNotThrow { SpringConversationsMethodGroup().kick("") }
    }

    @Test
    fun leave() {
        assertDoesNotThrow { SpringConversationsMethodGroup().leave("") }
    }

    @Test
    fun rename() {
        assertDoesNotThrow { SpringConversationsMethodGroup().rename("") }
    }

    @Test
    fun replies() {
        assertDoesNotThrow { SpringConversationsMethodGroup().replies("") }
    }

    @Test
    fun setPurpose() {
        assertDoesNotThrow { SpringConversationsMethodGroup().setPurpose("") }
    }

    @Test
    fun setTopic() {
        assertThrows<NotImplementedError>  { SpringConversationsMethodGroup().setTopic("") }
    }

    @Test
    fun unarchive() {
        assertDoesNotThrow { SpringConversationsMethodGroup().unarchive("") }
    }

    @Test
    fun archive() {
        assertDoesNotThrow { SpringConversationsMethodGroup().archive("") }
    }

    @Test
    fun members() {
        assertDoesNotThrow { SpringConversationsMethodGroup().members("") }
    }

    @Test
    fun open() {
        assertDoesNotThrow { SpringConversationsMethodGroup().open("") }
    }

    @Test
    fun list() {
        assertDoesNotThrow { SpringConversationsMethodGroup().list("") }
    }
}
