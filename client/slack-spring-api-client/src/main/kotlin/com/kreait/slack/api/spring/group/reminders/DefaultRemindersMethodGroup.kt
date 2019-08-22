package com.kreait.slack.api.spring.group.reminders

import com.kreait.slack.api.group.reminders.RemindersAddMethod
import com.kreait.slack.api.group.reminders.RemindersCompleteMethod
import com.kreait.slack.api.group.reminders.RemindersDeleteMethod
import com.kreait.slack.api.group.reminders.RemindersInfoMethod
import com.kreait.slack.api.group.reminders.RemindersListMethod
import com.kreait.slack.api.group.reminders.RemindersMethodGroup
import org.slf4j.LoggerFactory

class DefaultRemindersMethodGroup : RemindersMethodGroup {
    override fun delete(authToken: String): RemindersDeleteMethod {
        return DefaultRemindersDeleteMethod(authToken)
    }

    override fun list(authToken: String): RemindersListMethod {
        return DefaultRemindersListMethod(authToken)
    }

    override fun info(authToken: String): RemindersInfoMethod {
        return DefaultRemindersInfoMethod(authToken)
    }

    override fun complete(authToken: String): RemindersCompleteMethod {
        return DefaultRemindersCompleteMethod(authToken)
    }

    override fun add(authToken: String): RemindersAddMethod {
        return DefaultRemindersAddMethod(authToken)
    }

    companion object {
        val LOG = LoggerFactory.getLogger(DefaultRemindersMethodGroup::class.java)
    }
}
