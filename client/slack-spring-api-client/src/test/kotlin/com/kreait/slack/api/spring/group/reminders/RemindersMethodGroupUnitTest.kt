package com.kreait.slack.api.spring.group.reminders

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


@Deprecated("This test class is only used to fulfill coverage, it will be deleted when the all according methods are implemented")
class RemindersMethodGroupUnitTest() {


    @Test
    fun test() {
        val defaultRemindersMethodGroup = SpringRemindersMethodGroup()
        Assertions.assertTrue(defaultRemindersMethodGroup.delete("") is SpringRemindersDeleteMethod)
        Assertions.assertTrue(defaultRemindersMethodGroup.list("") is SpringRemindersListMethod)
        Assertions.assertTrue(defaultRemindersMethodGroup.info("") is SpringRemindersInfoMethod)
        Assertions.assertTrue(defaultRemindersMethodGroup.complete("") is SpringRemindersCompleteMethod)
        Assertions.assertTrue(defaultRemindersMethodGroup.add("") is SpringRemindersAddMethod)
    }
}
