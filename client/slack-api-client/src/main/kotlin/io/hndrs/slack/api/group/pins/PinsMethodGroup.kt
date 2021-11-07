package io.hndrs.slack.api.group.pins

/**
 * Convenience class to handle the groups operations
 *
 *  [Slack Api Documentation](https://api.slack.com/methods)
 */
interface PinsMethodGroup {

    /**
     * 	Pins an item to a channel.
     */
    fun add(authToken: String): PinsAddMethod

    /**
     * Lists items pinned to a channel.
     */
    fun list(authToken: String): PinsListMethod

    /**
     * Un-pins an item from a channel.
     */
    fun remove(authToken: String): PinsRemoveMethod
}
