package io.hndrs.slack.api.group.conversations

/**
 * Convenience class to handle the conversation operations
 *
 *  [Slack Api Documentation](https://api.slack.com/methods)
 */
@Suppress("ComplexInterface")
interface ConversationsMethodGroup {


    /**Archives a conversation.*/
    fun archive(authToken: String): io.hndrs.slack.api.group.conversations.ConversationsArchiveMethod

    /**Closes a direct message or multi-person direct message.*/
    fun close(authToken: String): io.hndrs.slack.api.group.conversations.ConversationsCloseMethod

    /**
     * Initiates a public or private channel-based conversation
     *
     * [SlackDock](https://api.slack.com/methods/conversations.create)
     *
     * @param authToken to authenticate against the slack api
     * @return RequestBuilder to create a conversation
     */
    fun create(authToken: String): io.hndrs.slack.api.group.conversations.ConversationsCreateMethod

    /**Fetches a conversation's history of messages and events.*/
    fun history(authToken: String): io.hndrs.slack.api.group.conversations.ConversationsHistoryMethod

    /**Retrieve information about a conversation.*/
    fun info(authToken: String): io.hndrs.slack.api.group.conversations.ConversationsInfoMethod

    /**Invites users to a channel.*/
    fun invite(authToken: String): io.hndrs.slack.api.group.conversations.ConversationsInviteMethod

    /**Joins an existing conversation.*/
    fun join(authToken: String): io.hndrs.slack.api.group.conversations.ConversationsJoinMethod

    /**Removes a user from a conversation.*/
    fun kick(authToken: String): io.hndrs.slack.api.group.conversations.ConversationsKickMethod

    /**Leaves a conversation.*/
    fun leave(authToken: String): io.hndrs.slack.api.group.conversations.ConversationsLeaveMethod

    /**Lists all channels in a Slack team.*/
    fun list(authToken: String): io.hndrs.slack.api.group.conversations.ConversationsListMethod

    /**Retrieve members of a conversation.*/
    fun members(authToken: String): io.hndrs.slack.api.group.conversations.ConversationsMembersMethod

    /**
     * This Conversations API method opens a multi-person direct message or just a 1:1 direct message.
     * [SlackDock](https://api.slack.com/methods/conversations.open)
     *
     * @param authToken to authenticate against the slack api
     * @return RequestBuilder to build the request
     */
    fun open(authToken: String): io.hndrs.slack.api.group.conversations.ConversationsOpenMethod

    /**Renames a conversation.*/
    fun rename(authToken: String): io.hndrs.slack.api.group.conversations.ConversationsRenameMethod

    /**Retrieve a thread of messages posted to a conversation*/
    fun replies(authToken: String): io.hndrs.slack.api.group.conversations.ConversationsRepliesMethod

    /**Sets the purpose for a conversation.*/
    fun setPurpose(authToken: String): io.hndrs.slack.api.group.conversations.ConversationsSetPurposeMethod

    /**Sets the topic for a conversation.*/
    fun setTopic(authToken: String): io.hndrs.slack.api.group.conversations.ConversationsSetTopicMethod

    /**Reverses conversation archival.*/
    fun unarchive(authToken: String): io.hndrs.slack.api.group.conversations.ConversationsUnarchiveMethod
}
