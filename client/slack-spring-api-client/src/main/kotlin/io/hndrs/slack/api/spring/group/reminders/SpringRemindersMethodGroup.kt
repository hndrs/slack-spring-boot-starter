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
class SpringRemindersMethodGroup : io.hndrs.slack.api.group.reminders.RemindersMethodGroup {

    override fun delete(authToken: String): io.hndrs.slack.api.group.reminders.RemindersDeleteMethod {
        return SpringRemindersDeleteMethod(authToken)
    }

    override fun list(authToken: String): io.hndrs.slack.api.group.reminders.RemindersListMethod {
        return SpringRemindersListMethod(authToken)
    }

    override fun info(authToken: String): io.hndrs.slack.api.group.reminders.RemindersInfoMethod {
        return SpringRemindersInfoMethod(authToken)
    }

    override fun complete(authToken: String): io.hndrs.slack.api.group.reminders.RemindersCompleteMethod {
        return SpringRemindersCompleteMethod(authToken)
    }

    override fun add(authToken: String): io.hndrs.slack.api.group.reminders.RemindersAddMethod {
        return SpringRemindersAddMethod(authToken)
    }

}
