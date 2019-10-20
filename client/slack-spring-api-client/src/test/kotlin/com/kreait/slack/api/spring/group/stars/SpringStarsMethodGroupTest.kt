package com.kreait.slack.api.spring.group.stars

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test

class SpringStarsMethodGroupTest {

    @Test
    fun add() {
        assertDoesNotThrow { SpringStarsMethodGroup().add("") }
    }

    @Test
    fun list() {
        assertDoesNotThrow { SpringStarsMethodGroup().list("") }
    }

    @Test
    fun remove() {
        assertDoesNotThrow { SpringStarsMethodGroup().remove("") }
    }
}
