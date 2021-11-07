package io.hndrs.slack.api.spring.group.reminders

import io.hndrs.slack.api.group.reminders.RemindersAddMethod
import io.hndrs.slack.api.group.reminders.RemindersCompleteMethod
import io.hndrs.slack.api.group.reminders.RemindersDeleteMethod
import io.hndrs.slack.api.group.reminders.RemindersInfoMethod
import io.hndrs.slack.api.group.reminders.RemindersListMethod
import io.hndrs.slack.api.group.reminders.RemindersMethodGroup

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
