package com.kreait.slack.api.group.groups

interface GroupsMethodGroup {

    /**
     * Archives a private channel.
     */
    fun archive(authToken: String): GroupsArchiveMethod

    /**
     *Creates a private channel.
     */
    fun create(authToken: String)

    /**
     *Clones and archives a private channel.
     */
    fun createChild(authToken: String)

    /**
     *Fetches history of messages and events from a private channel.
     */
    fun history(authToken: String)

    /**
     *Gets information about a private channel.
     */
    fun info(authToken: String)

    /**
     *Invites a user to a private channel.
     */
    fun invite(authToken: String)

    /**
     *Removes a user from a private channel.
     */
    fun kick(authToken: String)

    /**
     * Leaves a private channel.
     */
    fun leave(authToken: String)

    /**
     * Lists private channels that the calling user has access to.
     */
    fun list(authToken: String)

    /**
     * Sets the read cursor in a private channel.
     */
    fun mark(authToken: String)

    /**
     * Opens a private channel.
     */
    fun open(authToken: String)

    /**
     * Renames a private channel.
     */
    fun rename(authToken: String)

    /**
     * Retrieve a thread of messages posted to a private channel
     */
    fun replies(authToken: String)

    /**
     *  Sets the purpose for a private channel.
     */
    fun setPurpose(authToken: String)

    /**
     * Sets the topic for a private channel.
     */
    fun setTopic(authToken: String)

    /**
     * Unarchives a private channel.
     */
    fun unarchive(authToken: String)
}