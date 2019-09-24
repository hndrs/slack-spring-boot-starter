package com.kreait.slack.api.spring.group.usergroups

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test

internal class SpringUsergroupMethodGroupTest {

    @Test
    fun usersUpdate() {
        assertDoesNotThrow { SpringUsergroupMethodGroup().usersUpdate("") }
    }

    @Test
    fun create() {
        assertDoesNotThrow { SpringUsergroupMethodGroup().create("") }
    }

    @Test
    fun list() {
        assertDoesNotThrow { SpringUsergroupMethodGroup().list("") }
    }

    @Test
    fun update() {
        assertDoesNotThrow { SpringUsergroupMethodGroup().update("") }
    }

    @Test
    fun disable() {
        assertDoesNotThrow { SpringUsergroupMethodGroup().disable("") }
    }

    @Test
    fun usersList() {
        assertDoesNotThrow { SpringUsergroupMethodGroup().usersList("") }
    }

    @Test
    fun enable() {
        assertDoesNotThrow { SpringUsergroupMethodGroup().enable("") }
    }
}
