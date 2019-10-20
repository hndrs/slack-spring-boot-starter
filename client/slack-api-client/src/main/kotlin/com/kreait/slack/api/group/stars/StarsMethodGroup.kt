package com.kreait.slack.api.group.stars

/**
 * Convenience class to handle the groups operations
 *
 *  [Slack Api Documentation](https://api.slack.com/methods)
 */
interface StarsMethodGroup {
    /**
     * Adds a star to an item.
     */
    fun add(authToken: String): StarsAddMethod

    /**
     * 	Lists stars for a user.
     */
    fun list(authToken: String): StarsListMethod

    /**
     * Removes a star from an item.
     */
    fun remove(authToken: String): StarsRemoveMethod
}
