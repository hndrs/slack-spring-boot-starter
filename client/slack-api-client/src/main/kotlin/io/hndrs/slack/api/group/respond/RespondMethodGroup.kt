package io.hndrs.slack.api.group.respond

/**
 * Convenience class to handle the respond operations
 *
 *  [Slack Api Documentation](https://api.slack.com/methods)
 */
interface RespondMethodGroup {

    /**
     * sends a message response to the response-url
     */
    fun message(responseUrl: String): io.hndrs.slack.api.group.respond.RespondMessageMethod
}
