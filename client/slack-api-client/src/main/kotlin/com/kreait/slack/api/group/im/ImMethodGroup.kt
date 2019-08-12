package com.kreait.slack.api.group.im

interface ImMethodGroup {

    /**Close a direct message channel.*/
    fun close(authToken: String): ImCloseMethod

    /**Fetches history of messages and events from direct message channel.*/
    fun history(authToken: String): ImHistoryMethod

    /**Lists direct message channels for the calling user.*/
    fun list(authToken: String): ImListMethod

    /**Sets the read cursor in a direct message channel.*/
    fun mark(authToken: String): ImMarkMethod

    /**Opens a direct message channel.*/
    fun open(authToken: String): ImOpenMethod

    /**Retrieve a thread of messages posted to a direct message conversation*/
    fun replies(authToken: String): ImRepliesMethod

}
