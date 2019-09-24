package com.kreait.slack.api.spring.group.oauth

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class SpringOauthMethodGroupTest {

    @Test
    fun token() {
        assertThrows<NotImplementedError> { SpringOauthMethodGroup().token("") }
    }

    @Test
    fun access() {
        assertDoesNotThrow { SpringOauthMethodGroup().access() }
    }
}
