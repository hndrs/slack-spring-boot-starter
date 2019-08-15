package com.kreait.slack.api.test.group.chat

import com.kreait.slack.api.group.chat.ChatMethodGroup
import com.kreait.slack.api.group.chat.ChatUnfurlMethod
import com.kreait.slack.api.group.chat.ChatUpdateMethod

class MockChatMethodGroup : ChatMethodGroup {
    private val mockChatPostEphemeralMethod = MockChatPostEphemeral()
    private val mockChatPostMessageMethod = MockChatPostMessage()
    private val mockChatDeleteMethod = MockChatDelete()
    private val mockChatGetPermalinkMethod = MockChatGetPermalink()
    private val mockChatMeMessageMethod = MockChatMeMessage()
    private val mockChatUnfurl = MockChatUnfurl()

    override fun delete(authToken: String): MockChatDelete {
        return mockChatDeleteMethod
    }

    override fun getPermalink(authToken: String): MockChatGetPermalink {
        return mockChatGetPermalinkMethod
    }

    override fun meMessage(authToken: String): MockChatMeMessage {
        return mockChatMeMessageMethod
    }

    override fun postEphemeral(authToken: String): MockChatPostEphemeral {
        return mockChatPostEphemeralMethod
    }

    override fun postMessage(authToken: String): MockChatPostMessage {
        return mockChatPostMessageMethod
    }

    override fun unfurl(authToken: String): ChatUnfurlMethod {
        return mockChatUnfurl
    }

    override fun update(authToken: String): ChatUpdateMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
