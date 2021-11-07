package io.hndrs.slack.api.group.reminders

/**
 * Convenience class to handle the reminder operations
 *
 *  [Slack Api Documentation](https://api.slack.com/methods)
 */
interface RemindersMethodGroup {

    /**Deletes a reminder.*/
    fun delete(authToken: String): RemindersDeleteMethod

    /**Lists all reminders created by or for a given user.*/
    fun list(authToken: String): RemindersListMethod

    /**Gets information about a reminder.*/
    fun info(authToken: String): RemindersInfoMethod

    /**Marks a reminder as complete.*/
    fun complete(authToken: String): RemindersCompleteMethod

    /**Creates a reminder.*/
    fun add(authToken: String): RemindersAddMethod
}
