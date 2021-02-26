package io.hndrs.slack.api.spring.group.auth

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class SpringAuthMethodGroupTest {

    @Test
    fun revoke() {
        Assertions.assertDoesNotThrow { SpringAuthMethodGroup().revoke("") }
    }

    @Test
    fun test1() {
        Assertions.assertDoesNotThrow { SpringAuthMethodGroup().test("") }
    }
}
