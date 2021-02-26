package io.hndrs.slack.api.spring.group.pins

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test

class SpringPinsMethodGroupTest {

    @Test
    fun add() {
        assertDoesNotThrow { SpringPinsMethodGroup().add("") }
    }

    @Test
    fun list() {
        assertDoesNotThrow { SpringPinsMethodGroup().list("") }
    }

    @Test
    fun remove() {
        assertDoesNotThrow { SpringPinsMethodGroup().remove("") }
    }
}
