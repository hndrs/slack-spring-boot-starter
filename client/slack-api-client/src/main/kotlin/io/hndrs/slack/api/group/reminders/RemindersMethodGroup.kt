package io.hndrs.slack.api.group.reminders

/**
 * Convenience class to handle the reminder operations
 *
 *  [Slack Api Documentation](https://api.slack.com/methods)
 */
interface RemindersMethodGroup {

    /**Deletes a reminder.*/
    fun delete(authToken: String): io.hndrs.slack.api.group.reminders.RemindersDeleteMethod

    /**Lists all reminders created by or for a given user.*/
    fun list(authToken: String): io.hndrs.slack.api.group.reminders.RemindersListMethod

    /**Gets information about a reminder.*/
    fun info(authToken: String): io.hndrs.slack.api.group.reminders.RemindersInfoMethod

    /**Marks a reminder as complete.*/
    fun complete(authToken: String): io.hndrs.slack.api.group.reminders.RemindersCompleteMethod

    /**Creates a reminder.*/
    fun add(authToken: String): io.hndrs.slack.api.group.reminders.RemindersAddMethod
}
