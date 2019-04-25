package io.olaph.slack.client.group.respond

interface RespondMethodGroup {

    /**
     * sends an ephemeral response to the response-url
     */
    fun ephemeral(responseUrl: String): RespondEphemeralMethod


}