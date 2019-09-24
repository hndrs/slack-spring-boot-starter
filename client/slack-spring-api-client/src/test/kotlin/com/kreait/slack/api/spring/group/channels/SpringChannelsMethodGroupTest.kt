package com.kreait.slack.api.spring.group.channels

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class SpringChannelsMethodGroupTest {

    @Test
    fun archive() {
        assertDoesNotThrow { SpringChannelsMethodGroup().archive("") }
    }

    @Test
    fun create() {
        assertDoesNotThrow { SpringChannelsMethodGroup().create("") }
    }

    @Test
    fun history() {
        assertDoesNotThrow { SpringChannelsMethodGroup().history("") }
    }

    @Test
    fun invite() {
        assertDoesNotThrow { SpringChannelsMethodGroup().invite("") }
    }

    @Test
    fun join() {
        assertThrows<NotImplementedError> { SpringChannelsMethodGroup().join("") }
    }

    @Test
    fun kick() {
        assertDoesNotThrow { SpringChannelsMethodGroup().kick("") }
    }

    @Test
    fun leave() {
        assertDoesNotThrow { SpringChannelsMethodGroup().leave("") }
    }

    @Test
    fun mark() {
        assertDoesNotThrow { SpringChannelsMethodGroup().mark("") }
    }

    @Test
    fun rename() {
        assertThrows<NotImplementedError> { SpringChannelsMethodGroup().rename("") }
    }

    @Test
    fun replies() {
        assertDoesNotThrow { SpringChannelsMethodGroup().replies("") }
    }

    @Test
    fun setPurpose() {
        assertDoesNotThrow { SpringChannelsMethodGroup().setPurpose("") }
    }

    @Test
    fun setTopic() {
        assertDoesNotThrow { SpringChannelsMethodGroup().setTopic("") }
    }

    @Test
    fun unarchive() {
        assertThrows<NotImplementedError> { SpringChannelsMethodGroup().unarchive("") }
    }

    @Test
    fun info() {
        assertDoesNotThrow { SpringChannelsMethodGroup().info("") }
    }
}
