package com.kreait.slack.api.spring.group.dialog

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test

internal class SpringDialogMethodGroupTest {

    @Test
    fun open() {
        assertDoesNotThrow { SpringDialogMethodGroup().open("") }
    }
}
