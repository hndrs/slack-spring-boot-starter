package io.olaph.slack.client.test

import io.olaph.slack.client.SlackClient
import io.olaph.slack.client.test.group.auth.MockAuthGroup
import io.olaph.slack.client.test.group.channel.MockChannelsMethodGroup
import io.olaph.slack.client.test.group.chat.MockChatGroup
import io.olaph.slack.client.test.group.conversation.MockConversationMethodGroup
import io.olaph.slack.client.test.group.dialog.MockDialogMethodGroup
import io.olaph.slack.client.test.group.im.MockImMethodGroup
import io.olaph.slack.client.test.group.oauth.MockOauthMethodGroup
import io.olaph.slack.client.test.group.users.MockUsersMethodGroup

class MockSlackClient : SlackClient {

    private val mockChatGroup = MockChatGroup()
    private val mockAuthGroup = MockAuthGroup()
    private val mockImMethodGroup = MockImMethodGroup()
    private val mockDialogMethodGroup = MockDialogMethodGroup()
    private val mockConversationsMethodGroup = MockConversationMethodGroup()
    private val mockChannelsMethodGroup = MockChannelsMethodGroup()
    private val mockUsersMethodGroup = MockUsersMethodGroup()
    private val mockOauthMethodGroup = MockOauthMethodGroup()

    override fun auth(): MockAuthGroup {
        return mockAuthGroup
    }

    override fun chat(): MockChatGroup {
        return mockChatGroup
    }

    override fun dialog(): MockDialogMethodGroup {
        return mockDialogMethodGroup
    }

    override fun conversation(): MockConversationMethodGroup {
        return mockConversationsMethodGroup
    }

    override fun channel(): MockChannelsMethodGroup {
        return mockChannelsMethodGroup
    }

    override fun im(): MockImMethodGroup {
        return mockImMethodGroup
    }

    override fun users(): MockUsersMethodGroup {
        return mockUsersMethodGroup
    }

    override fun oauth(): MockOauthMethodGroup {
        return mockOauthMethodGroup
    }
}
