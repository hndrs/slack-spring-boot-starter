package com.kreait.slack.api.group.respond

interface RespondMethodGroup {

    /**
     * sends a message response to the response-url
     */
    fun message(responseUrl: String): RespondMessageMethod


}
