package io.olaph.slack.client.test

import io.olaph.slack.client.SlackClient
import io.olaph.slack.client.group.respond.RespondMethodGroup
import io.olaph.slack.client.group.team.TeamMethodGroup
import io.olaph.slack.client.test.group.auth.MockAuthGroup
import io.olaph.slack.client.test.group.channel.MockChannelsMethodGroup
import io.olaph.slack.client.test.group.chat.MockChatMethodGroup
import io.olaph.slack.client.test.group.conversation.MockConversationMethodGroup
import io.olaph.slack.client.test.group.dialog.MockDialogMethodGroup
import io.olaph.slack.client.test.group.im.MockImMethodGroup
import io.olaph.slack.client.test.group.oauth.MockOauthMethodGroup
import io.olaph.slack.client.test.group.respond.MockRespondMethodGroup
import io.olaph.slack.client.test.group.team.MockTeamMethodGroup
import io.olaph.slack.client.test.group.users.MockUsersMethodGroup

class MockSlackClient : SlackClient {
    private val mockChatGroup = MockChatMethodGroup()

    private val mockAuthGroup = MockAuthGroup()
    private val mockImMethodGroup = MockImMethodGroup()
    private val mockDialogMethodGroup = MockDialogMethodGroup()
    private val mockConversationsMethodGroup = MockConversationMethodGroup()
    private val mockChannelsMethodGroup = MockChannelsMethodGroup()
    private val mockUsersMethodGroup = MockUsersMethodGroup()
    private val mockOauthMethodGroup = MockOauthMethodGroup()
    private val mockRespondMethodGroup = MockRespondMethodGroup()
    private val mockTeamMethodGroup = MockTeamMethodGroup()
    override fun auth(): MockAuthGroup {
        return mockAuthGroup
    }

    override fun chat(): MockChatMethodGroup {
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

    override fun respond(): RespondMethodGroup {
        return mockRespondMethodGroup;
    }

    override fun team(): TeamMethodGroup {
        return mockTeamMethodGroup
    }
}
