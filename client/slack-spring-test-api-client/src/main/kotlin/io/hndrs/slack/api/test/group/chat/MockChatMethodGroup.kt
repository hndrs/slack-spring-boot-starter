package io.hndrs.slack.api.test.group.chat

import io.hndrs.slack.api.group.chat.ChatMethodGroup

/**
 * Testable implementation of [ChatMethodGroup]
 */
class MockChatMethodGroup : io.hndrs.slack.api.group.chat.ChatMethodGroup {

    private val mockChatPostEphemeralMethod = MockChatPostEphemeral()
    private val mockChatPostMessageMethod = MockChatPostMessage()
    private val mockChatDeleteMethod = MockChatDelete()
    private val mockChatGetPermalinkMethod = MockChatGetPermalink()
    private val mockChatMeMessageMethod = MockChatMeMessage()
    private val mockChatUnfurlMethod = MockChatUnfurl()
    private val mockChatUpdateMethod = MockChatUpdate()

    override fun delete(authToken: String) = mockChatDeleteMethod
    override fun getPermalink(authToken: String) = mockChatGetPermalinkMethod
    override fun meMessage(authToken: String) = mockChatMeMessageMethod
    override fun postEphemeral(authToken: String) = mockChatPostEphemeralMethod
    override fun postMessage(authToken: String) = mockChatPostMessageMethod
    override fun unfurl(authToken: String) = mockChatUnfurlMethod
    override fun update(authToken: String) = mockChatUpdateMethod
}
