package com.kreait.slack.api.group.chat

interface ChatMethodGroup {

    //TODO DOC
    fun delete(authToken: String): ChatDeleteMethod

    //TODO DOC
    fun getPermalink(authToken: String): GetChatPermalinkMethod

    //TODO DOC
    fun meMessage(authToken: String): ChatMeMessageMethod

    //TODO DOC
    fun postEphemeral(authToken: String): ChatPostEphemeralMethod

    //TODO DOC
    fun postMessage(authToken: String): ChatPostMessageMethod

    //TODO DOC
    fun unfurl(authToken: String): ChatUnfurlMethod

    //TODO DOC
    fun update(authToken: String): ChatUpdateMethod
}
