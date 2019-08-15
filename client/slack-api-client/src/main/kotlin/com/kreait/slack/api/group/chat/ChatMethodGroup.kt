package com.kreait.slack.api.group.chat

interface ChatMethodGroup {

    /**
     * Deletes a Message from the chat
     * https://api.slack.com/methods/chat.delete
     */
    fun delete(authToken: String): ChatDeleteMethod

    /**
     * Retrieve a permalink URL for a specific extant message
     * https://api.slack.com/methods/chat.getPermalink
     */
    fun getPermalink(authToken: String): ChatGetPermalinkMethod

    /**
     * Shares a me message into a channel.
     * https://api.slack.com/methods/chat.meMessage
     */
    fun meMessage(authToken: String): ChatMeMessageMethod

    /**
     * Sends an ephemeral message a channel
     * Ephemeral messages are not persisted in the slack-database and can't be updated
     * If possible, use slackclient.respond() with the response-url of e.g. a slack-command in order to be context independent
     * https://api.slack.com/methods/chat.postEphemeral
     */
    fun postEphemeral(authToken: String): ChatPostEphemeralMethod

    /**
     * sends a message to a channel
     * https://api.slack.com/methods/chat.postMessage
     */
    fun postMessage(authToken: String): ChatPostMessageMethod

    /**
     * Provide custom unfurl behavior for user-posted URLs
     * https://api.slack.com/methods/chat.unfurl
     */
    fun unfurl(authToken: String): ChatUnfurlMethod

    /**
     * Updates a message.
     * hint: ephemeral messages can't be updated, since they are not persisted in the Slack-Database
     * https://api.slack.com/methods/chat.update
     */
    fun update(authToken: String): ChatUpdateMethod
}
