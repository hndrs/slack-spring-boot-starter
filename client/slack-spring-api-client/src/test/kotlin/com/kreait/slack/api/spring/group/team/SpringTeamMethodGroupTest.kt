package com.kreait.slack.api.spring.group.team

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test

internal class SpringTeamMethodGroupTest {

    @Test
    fun getProfile() {
        assertDoesNotThrow { SpringTeamMethodGroup().getProfile("") }
    }
}
