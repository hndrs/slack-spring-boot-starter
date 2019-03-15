package io.olaph.slack.client.test.group.chat

import io.olaph.slack.client.group.chat.ChatDeleteMethod
import io.olaph.slack.client.group.chat.ChatMeMessageMethod
import io.olaph.slack.client.group.chat.ChatMethodGroup
import io.olaph.slack.client.group.chat.ChatUnfurlMethod
import io.olaph.slack.client.group.chat.ChatUpdateMethod
import io.olaph.slack.client.group.chat.GetChatPermalinkMethod

class MockChatGroup : ChatMethodGroup {

    private val mockChatPostEphemeralMethod = MockChatPostEphemeralMethod()
    private val mockChatPostMessageMethod = MockChatPostMessageMethod()

    override fun delete(authToken: String): ChatDeleteMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPermalink(authToken: String): GetChatPermalinkMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun meMessage(authToken: String): ChatMeMessageMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun postEphemeral(authToken: String): MockChatPostEphemeralMethod {
        return mockChatPostEphemeralMethod
    }

    override fun postMessage(authToken: String): MockChatPostMessageMethod {
        return mockChatPostMessageMethod
    }

    override fun unfurl(authToken: String): ChatUnfurlMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(authToken: String): ChatUpdateMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
