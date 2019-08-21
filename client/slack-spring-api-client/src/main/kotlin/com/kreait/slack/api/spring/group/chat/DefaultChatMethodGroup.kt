package com.kreait.slack.api.spring.group.chat

import com.kreait.slack.api.group.chat.ChatMethodGroup

class DefaultChatMethodGroup : ChatMethodGroup {

    override fun getPermalink(authToken: String): DefaultGetPermalinkMethod {
        return DefaultGetPermalinkMethod(authToken)
    }

    override fun meMessage(authToken: String): DefaultMeMessageMethod {
        return DefaultMeMessageMethod(authToken)
    }

    override fun unfurl(authToken: String): DefaultUnfurlMethod {
        return DefaultUnfurlMethod(authToken)
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
