package com.kreait.slack.api.spring.group.respond

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test

internal class SpringRespondMethodGroupTest {

    @Test
    fun message() {
        assertDoesNotThrow { SpringRespondMethodGroup().message("") }
    }
}
