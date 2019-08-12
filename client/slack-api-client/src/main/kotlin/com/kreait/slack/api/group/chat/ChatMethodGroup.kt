package com.kreait.slack.api.group.chat

import com.kreait.slack.api.group.chat.ChatDeleteMethod
import com.kreait.slack.api.group.chat.ChatMeMessageMethod
import com.kreait.slack.api.group.chat.ChatPostEphemeralMethod
import com.kreait.slack.api.group.chat.ChatPostMessageMethod
import com.kreait.slack.api.group.chat.ChatUnfurlMethod
import com.kreait.slack.api.group.chat.ChatUpdateMethod
import com.kreait.slack.api.group.chat.GetChatPermalinkMethod

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
