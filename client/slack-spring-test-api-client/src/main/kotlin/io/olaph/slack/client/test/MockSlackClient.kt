package io.olaph.slack.client.test

import io.olaph.slack.client.SlackClient
import io.olaph.slack.client.group.channels.ChannelsMethodGroup
import io.olaph.slack.client.group.conversations.ConversationsMethodGroup
import io.olaph.slack.client.group.oauth.OauthMethodGroup
import io.olaph.slack.client.group.users.UsersMethodGroup
import io.olaph.slack.client.test.group.auth.MockAuthGroup
import io.olaph.slack.client.test.group.chat.MockChatGroup
import io.olaph.slack.client.test.group.dialog.MockDialogMethodGroup
import io.olaph.slack.client.test.group.im.MockImMethodGroup

class MockSlackClient : SlackClient {

    private val mockChatGroup = MockChatGroup()
    private val mockAuthGroup = MockAuthGroup()
    private val mockImMethodGroup = MockImMethodGroup()
    private val mockDialogMethodGroup = MockDialogMethodGroup()

    override fun auth(): MockAuthGroup {
        return mockAuthGroup
    }

    override fun chat(): MockChatGroup {
        return mockChatGroup
    }

    override fun dialog(): MockDialogMethodGroup {
        return mockDialogMethodGroup
    }

    override fun conversation(): ConversationsMethodGroup {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun channel(): ChannelsMethodGroup {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun im(): MockImMethodGroup {
        return mockImMethodGroup
    }

    override fun users(): UsersMethodGroup {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun oauth(): OauthMethodGroup {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
