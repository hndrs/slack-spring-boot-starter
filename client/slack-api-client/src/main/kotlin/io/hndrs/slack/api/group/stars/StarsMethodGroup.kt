package io.hndrs.slack.api.group.stars

/**
 * Convenience class to handle the groups operations
 *
 *  [Slack Api Documentation](https://api.slack.com/methods)
 */
interface StarsMethodGroup {
    /**
     * Adds a star to an item.
     */
    fun add(authToken: String): io.hndrs.slack.api.group.stars.StarsAddMethod

    /**
     * 	Lists stars for a user.
     */
    fun list(authToken: String): io.hndrs.slack.api.group.stars.StarsListMethod

    /**
     * Removes a star from an item.
     */
    fun remove(authToken: String): io.hndrs.slack.api.group.stars.StarsRemoveMethod
}
