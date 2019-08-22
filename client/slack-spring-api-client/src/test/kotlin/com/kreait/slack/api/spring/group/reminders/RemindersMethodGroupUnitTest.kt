package com.kreait.slack.api.spring.group.reminders

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


@Deprecated("This test class is only used to fulfill coverage, it will be deleted when the all according methods are implemented")
class RemindersMethodGroupUnitTest() {


    @Test
    fun test() {
        val defaultRemindersMethodGroup = DefaultRemindersMethodGroup()
        Assertions.assertTrue(defaultRemindersMethodGroup.delete("") is DefaultRemindersDeleteMethod)
        Assertions.assertTrue(defaultRemindersMethodGroup.list("") is DefaultRemindersListMethod)
        Assertions.assertTrue(defaultRemindersMethodGroup.info("") is DefaultRemindersInfoMethod)
        Assertions.assertTrue(defaultRemindersMethodGroup.complete("") is DefaultRemindersCompleteMethod)
        Assertions.assertTrue(defaultRemindersMethodGroup.add("") is DefaultRemindersAddMethod)
    }
}