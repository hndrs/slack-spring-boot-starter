package com.kreait.slack.api.group.respond

/**
 * Convenience class to handle the respond operations
 *
 *  [Slack Api Documentation](https://api.slack.com/methods)
 */
interface RespondMethodGroup {

    /**
     * sends a message response to the response-url
     */
    fun message(responseUrl: String): RespondMessageMethod
}
