package com.kreait.slack.api.group.groups

interface GroupsMethodGroup {

    /**
     * Archives a private channel.
     */
    fun archive()

    /**
     *Creates a private channel.
     */
    fun create()

    /**
     *Clones and archives a private channel.
     */
    fun createChild()

    /**
     *Fetches history of messages and events from a private channel.
     */
    fun history()

    /**
     *Gets information about a private channel.
     */
    fun info()

    /**
     *Invites a user to a private channel.
     */
    fun invite()

    /**
     *Removes a user from a private channel.
     */
    fun kick()

    /**
     * Leaves a private channel.
     */
    fun leave()

    /**
     * Lists private channels that the calling user has access to.
     */
    fun list()

    /**
     * Sets the read cursor in a private channel.
     */
    fun mark()

    /**
     * Opens a private channel.
     */
    fun open()

    /**
     * Renames a private channel.
     */
    fun rename()

    /**
     * Retrieve a thread of messages posted to a private channel
     */
    fun replies()

    /**
     *  Sets the purpose for a private channel.
     */
    fun setPurpose()

    /**
     * Sets the topic for a private channel.
     */
    fun setTopic()

    /**
     * Unarchives a private channel.
     */
    fun unarchive()
}