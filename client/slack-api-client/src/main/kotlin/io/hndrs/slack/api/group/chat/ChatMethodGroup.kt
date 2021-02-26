package io.hndrs.slack.api.group.chat

/**
 * Convenience class to handle the chat operations
 *
 *  [Slack Api Documentation](https://api.slack.com/methods)
 */
interface ChatMethodGroup {

    /**
     * Deletes a Message from the chat
     * https://api.slack.com/methods/chat.delete
     */
    fun delete(authToken: String): io.hndrs.slack.api.group.chat.ChatDeleteMethod

    /**
     * Retrieve a permalink URL for a specific extant message
     * https://api.slack.com/methods/chat.getPermalink
     */
    fun getPermalink(authToken: String): io.hndrs.slack.api.group.chat.ChatGetPermalinkMethod

    /**
     * Shares a me message into a channel.
     * https://api.slack.com/methods/chat.meMessage
     */
    fun meMessage(authToken: String): io.hndrs.slack.api.group.chat.ChatMeMessageMethod

    /**
     * Sends an ephemeral message a channel
     * Ephemeral messages are not persisted in the slack-database and can't be updated
     * If possible, use slackclient.respond() with the response-url of e.g. a slack-command in order to be context independent
     * https://api.slack.com/methods/chat.postEphemeral
     */
    fun postEphemeral(authToken: String): io.hndrs.slack.api.group.chat.ChatPostEphemeralMethod

    /**
     * sends a message to a channel
     * https://api.slack.com/methods/chat.postMessage
     */
    fun postMessage(authToken: String): io.hndrs.slack.api.group.chat.ChatPostMessageMethod

    /**
     * Provide custom unfurl behavior for user-posted URLs
     * https://api.slack.com/methods/chat.unfurl
     */
    fun unfurl(authToken: String): io.hndrs.slack.api.group.chat.ChatUnfurlMethod

    /**
     * Updates a message.
     * hint: ephemeral messages can't be updated, since they are not persisted in the Slack-Database
     * https://api.slack.com/methods/chat.update
     */
    fun update(authToken: String): io.hndrs.slack.api.group.chat.ChatUpdateMethod
}
