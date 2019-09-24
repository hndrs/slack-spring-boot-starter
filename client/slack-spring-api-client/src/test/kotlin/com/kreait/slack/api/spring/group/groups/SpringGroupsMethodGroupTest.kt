package com.kreait.slack.api.spring.group.groups

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test

internal class SpringGroupsMethodGroupTest {

    @Test
    fun archive() {
        assertDoesNotThrow { SpringGroupsMethodGroup().archive("") }
    }

    @Test
    fun create() {
        assertDoesNotThrow { SpringGroupsMethodGroup().create("") }
    }

    @Test
    fun createChild() {
        assertDoesNotThrow { SpringGroupsMethodGroup().createChild("") }
    }

    @Test
    fun history() {
        assertDoesNotThrow { SpringGroupsMethodGroup().history("") }
    }

    @Test
    fun info() {
        assertDoesNotThrow { SpringGroupsMethodGroup().info("") }
    }

    @Test
    fun invite() {
        assertDoesNotThrow { SpringGroupsMethodGroup().invite("") }
    }

    @Test
    fun kick() {
        assertDoesNotThrow { SpringGroupsMethodGroup().kick("") }
    }

    @Test
    fun leave() {
        assertDoesNotThrow { SpringGroupsMethodGroup().leave("") }
    }

    @Test
    fun list() {
        assertDoesNotThrow { SpringGroupsMethodGroup().list("") }
    }

    @Test
    fun mark() {
        assertDoesNotThrow { SpringGroupsMethodGroup().mark("") }
    }

    @Test
    fun open() {
        assertDoesNotThrow { SpringGroupsMethodGroup().open("") }
    }

    @Test
    fun rename() {
        assertDoesNotThrow { SpringGroupsMethodGroup().rename("") }
    }

    @Test
    fun replies() {
        assertDoesNotThrow { SpringGroupsMethodGroup().replies("") }
    }

    @Test
    fun setPurpose() {
        assertDoesNotThrow { SpringGroupsMethodGroup().setPurpose("") }
    }

    @Test
    fun setTopic() {
        assertDoesNotThrow { SpringGroupsMethodGroup().setTopic("") }
    }

    @Test
    fun unarchive() {
        assertDoesNotThrow { SpringGroupsMethodGroup().unarchive("") }
    }
}
