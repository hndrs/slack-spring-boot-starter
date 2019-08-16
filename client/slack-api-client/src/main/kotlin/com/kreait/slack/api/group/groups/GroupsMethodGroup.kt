package com.kreait.slack.api.group.groups

interface GroupsMethodGroup {

    /**
     * Archives a private channel.
     */
    fun archive(authToken: String): GroupsArchiveMethod

    /**
     *Creates a private channel.
     */
    fun create(authToken: String): GroupsCreateMethod

    /**
     *Clones and archives a private channel.
     */
    fun createChild(authToken: String): GroupsCreateChildMethod

    /**
     *Fetches history of messages and events from a private channel.
     */
    fun history(authToken: String): GroupsHistoryMethod

    /**
     *Gets information about a private channel.
     */
    fun info(authToken: String): GroupsInfoMethod

    /**
     *Invites a user to a private channel.
     */
    fun invite(authToken: String): GroupsInviteMethod

    /**
     *Removes a user from a private channel.
     */
    fun kick(authToken: String): GroupsKickMethod

    /**
     * Leaves a private channel.
     */
    fun leave(authToken: String): GroupsLeaveMethod

    /**
     * Lists private channels that the calling user has access to.
     */
    fun list(authToken: String): GroupsListMethod

    /**
     * Sets the read cursor in a private channel.
     */
    fun mark(authToken: String): GroupsMarkMethod

    /**
     * Opens a private channel.
     */
    fun open(authToken: String): GroupsOpenMethod

    /**
     * Renames a private channel.
     */
    fun rename(authToken: String): GroupsRenameMethod

    /**
     * Retrieve a thread of messages posted to a private channel
     */
    fun replies(authToken: String): GroupsRepliesMethod

    /**
     *  Sets the purpose for a private channel.
     */
    fun setPurpose(authToken: String): GroupsSetPurposeMethod

    /**
     * Sets the topic for a private channel.
     */
    fun setTopic(authToken: String): GroupsSetTopicMethod

    /**
     * Unarchives a private channel.
     */
    fun unarchive(authToken: String): GroupsUnarchiveMethod
}