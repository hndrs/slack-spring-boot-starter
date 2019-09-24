package com.kreait.slack.api.spring.group.reminders

import com.kreait.slack.api.group.reminders.RemindersAddMethod
import com.kreait.slack.api.group.reminders.RemindersCompleteMethod
import com.kreait.slack.api.group.reminders.RemindersDeleteMethod
import com.kreait.slack.api.group.reminders.RemindersInfoMethod
import com.kreait.slack.api.group.reminders.RemindersListMethod
import com.kreait.slack.api.group.reminders.RemindersMethodGroup

/**
 * Convenience function to apply slack api reminders method grouping
 *
 * [Slack Api Documentation](https://api.slack.com/methods)
 */
class SpringRemindersMethodGroup : RemindersMethodGroup {

    override fun delete(authToken: String): RemindersDeleteMethod {
        return SpringRemindersDeleteMethod(authToken)
    }

    override fun list(authToken: String): RemindersListMethod {
        return SpringRemindersListMethod(authToken)
    }

    override fun info(authToken: String): RemindersInfoMethod {
        return SpringRemindersInfoMethod(authToken)
    }

    override fun complete(authToken: String): RemindersCompleteMethod {
        return SpringRemindersCompleteMethod(authToken)
    }

    override fun add(authToken: String): RemindersAddMethod {
        return SpringRemindersAddMethod(authToken)
    }

}
