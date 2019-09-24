package com.kreait.slack.api.spring.group.im

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test

internal class SpringImMethodGroupTest {

    @Test
    fun history() {
        assertDoesNotThrow { SpringImMethodGroup().history("") }
    }

    @Test
    fun list() {
        assertDoesNotThrow { SpringImMethodGroup().list("") }
    }

    @Test
    fun mark() {
        assertDoesNotThrow { SpringImMethodGroup().mark("") }
    }

    @Test
    fun replies() {
        assertDoesNotThrow { SpringImMethodGroup().replies("") }
    }

    @Test
    fun close() {
        assertDoesNotThrow { SpringImMethodGroup().close("") }
    }

    @Test
    fun open() {
        assertDoesNotThrow { SpringImMethodGroup().open("") }
    }
}
