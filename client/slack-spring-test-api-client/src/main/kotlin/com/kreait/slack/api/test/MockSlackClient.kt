package com.kreait.slack.api.test

import com.kreait.slack.api.SlackClient
import com.kreait.slack.api.group.reminders.RemindersMethodGroup
import com.kreait.slack.api.test.group.auth.MockAuthGroup
import com.kreait.slack.api.test.group.channel.MockChannelsMethodGroup
import com.kreait.slack.api.test.group.chat.MockChatMethodGroup
import com.kreait.slack.api.test.group.conversation.MockConversationMethodGroup
import com.kreait.slack.api.test.group.dialog.MockDialogMethodGroup
import com.kreait.slack.api.test.group.groups.MockGroupsMethodGroup
import com.kreait.slack.api.test.group.im.MockImMethodGroup
import com.kreait.slack.api.test.group.oauth.MockOauthMethodGroup
import com.kreait.slack.api.test.group.reminders.MockRemindersMethodGroup
import com.kreait.slack.api.test.group.respond.MockRespondMethodGroup
import com.kreait.slack.api.test.group.team.MockTeamMethodGroup
import com.kreait.slack.api.test.group.usergroups.MockUsergroupsMethodGroup
import com.kreait.slack.api.test.group.users.MockUsersMethodGroup

/**
 * [SlackClient] implementation for testing purposes
 *
 */
class MockSlackClient : SlackClient {
    private val mockRemindersMethodGroup = MockRemindersMethodGroup()
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
    private val mockUsergroupsMethodGroup = MockUsergroupsMethodGroup()
    private val mockGroupsMethodGroup = MockGroupsMethodGroup()

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

    override fun respond(): MockRespondMethodGroup {
        return mockRespondMethodGroup;
    }

    override fun team(): MockTeamMethodGroup {
        return mockTeamMethodGroup
    }

    override fun usergroups(): MockUsergroupsMethodGroup {
        return mockUsergroupsMethodGroup
    }

    override fun groups(): MockGroupsMethodGroup {
        return mockGroupsMethodGroup
    }

    override fun reminders(): RemindersMethodGroup {
        return mockRemindersMethodGroup
    }
}
