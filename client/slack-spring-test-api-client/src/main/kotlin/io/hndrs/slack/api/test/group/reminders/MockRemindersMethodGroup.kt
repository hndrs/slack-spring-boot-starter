package io.hndrs.slack.api.test.group.reminders


import io.hndrs.slack.api.group.reminders.RemindersMethodGroup

/**
 * Testable implementation of [RemindersMethodGroup]
 */
class MockRemindersMethodGroup : io.hndrs.slack.api.group.reminders.RemindersMethodGroup {

    private val mockRemindersDeleteMethod = MockRemindersDeleteMethod()
    private val mockRemindersAddMethod = MockRemindersAddMethod()
    private val mockRemindersInfoMethod = MockRemindersInfoMethod()
    private val mockRemindersCompleteMethod = MockRemindersCompleteMethod()
    private val mockRemindersListMethod = MockRemindersListMethod()

    override fun list(authToken: String): MockRemindersListMethod = mockRemindersListMethod
    override fun info(authToken: String): MockRemindersInfoMethod = mockRemindersInfoMethod
    override fun complete(authToken: String): MockRemindersCompleteMethod = mockRemindersCompleteMethod
    override fun add(authToken: String): MockRemindersAddMethod = mockRemindersAddMethod
    override fun delete(authToken: String): MockRemindersDeleteMethod = mockRemindersDeleteMethod
}
