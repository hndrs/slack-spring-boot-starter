package io.olaph.slack.client.group.conversations

interface ConversationsMethodGroup {


    /**Archives a conversation.*/
    fun archive(authToken: String): ConversationsArchiveMethod

    /**Closes a direct message or multi-person direct message.*/
    fun close(authToken: String): ConversationsCloseMethod

    /**
     * Initiates a public or private channel-based conversation
     *
     * [SlackDock](https://api.slack.com/methods/conversations.create)
     *
     * @param authToken to authenticate against the slack api
     * @return RequestBuilder to create a conversation
     */
    fun create(authToken: String): ConversationsCreateMethod

    /**Fetches a conversation's history of messages and events.*/
    fun history(authToken: String): ConversationsHistoryMethod

    /**Retrieve information about a conversation.*/
    fun info(authToken: String): ConversationsInfoMethod

    /**Invites users to a channel.*/
    fun invite(authToken: String): ConversationsInviteMethod

    /**Joins an existing conversation.*/
    fun join(authToken: String): ConversationsJoinMethod

    /**Removes a user from a conversation.*/
    fun kick(authToken: String): ConversationsKickMethod

    /**Leaves a conversation.*/
    fun leave(authToken: String): ConversationsLeaveMethod

    /**Lists all channels in a Slack team.*/
    fun list(authToken: String): ConversationsListMethod

    /**Retrieve members of a conversation.*/
    fun members(authToken: String): ConversationsMembersMethod

    /**
     * This Conversations API method opens a multi-person direct message or just a 1:1 direct message.
     * [SlackDock](https://api.slack.com/methods/conversations.open)
     *
     * @param authToken to authenticate against the slack api
     * @return RequestBuilder to build the request
     */
    fun open(authToken: String): ConversationsOpenMethod

    /**Renames a conversation.*/
    fun rename(authToken: String): ConversationsRenameMethod

    /**Retrieve a thread of messages posted to a conversation*/
    fun replies(authToken: String): ConversationsRepliesMethod

    /**Sets the purpose for a conversation.*/
    fun setPurpose(authToken: String): ConversationsSetPurposeMethod

    /**Sets the topic for a conversation.*/
    fun setTopic(authToken: String): ConversationsSetTopicMethod

    /**Reverses conversation archival.*/
    fun unarchive(authToken: String): ConversationsUnarchiveMethod
}
