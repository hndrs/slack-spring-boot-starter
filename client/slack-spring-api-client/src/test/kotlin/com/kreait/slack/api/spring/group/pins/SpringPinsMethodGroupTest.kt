package com.kreait.slack.api.spring.group.pins

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test

class SpringPinsMethodGroupTest {

    @Test
    fun history() {
        assertDoesNotThrow { SpringPinsMethodGroup().add("") }
    }

    @Test
    fun list() {
        assertDoesNotThrow { SpringPinsMethodGroup().list("") }
    }

    @Test
    fun mark() {
        assertDoesNotThrow { SpringPinsMethodGroup().remove("") }
    }
}