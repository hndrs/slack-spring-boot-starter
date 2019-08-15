package com.kreait.slack.api.spring.group.chat

import com.kreait.slack.api.group.chat.ChatGetPermalinkMethod
import com.kreait.slack.api.group.chat.ChatMeMessageMethod
import com.kreait.slack.api.group.chat.ChatMethodGroup
import com.kreait.slack.api.group.chat.ChatUnfurlMethod
import org.slf4j.LoggerFactory

class DefaultChatMethodGroup : ChatMethodGroup {
    override fun getPermalink(authToken: String): ChatGetPermalinkMethod {
        return DefaultGetPermalinkMethod(authToken)
    }

    override fun meMessage(authToken: String): ChatMeMessageMethod {
        return DefaultMeMessageMethod(authToken)
    }

    override fun unfurl(authToken: String): ChatUnfurlMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        val LOG = LoggerFactory.getLogger(DefaultChatMethodGroup::class.java)
    }

    override fun delete(authToken: String): DefaultDeleteMethod {
        return DefaultDeleteMethod(authToken)
    }

    override fun postMessage(authToken: String): DefaultPostMessageMethod {
        return DefaultPostMessageMethod(authToken)
    }

    override fun postEphemeral(authToken: String): DefaultPostEphemeralMethod {
        return DefaultPostEphemeralMethod(authToken)
    }

    override fun update(authToken: String): DefaultUpdateMethod {
        return DefaultUpdateMethod(authToken)
    }
}
