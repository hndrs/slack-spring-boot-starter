package com.kreait.slack.api.test.group.groups

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Groups.archive Method")
class MockGroupsCreateUnitTest {

    @DisplayName("Mocking Success")
    @Test
    fun testMock() {
        val group = MockGroupsMethodGroup()
        Assertions.assertTrue(group.create("") is MockGroupsCreateMethod)
    }
}
