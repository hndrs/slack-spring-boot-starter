package com.kreait.slack.api.test

import com.kreait.slack.api.SlackClient
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

class MockSlackClient : SlackClient {
    private val mockRemindersMethodGroup = MockRemindersMethodGroup()
    private val mockChatMethodGroup = MockChatMethodGroup()
    private val mockAuthMethodGroup = MockAuthGroup()
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

    override fun auth() = mockAuthMethodGroup
    override fun chat() = mockChatMethodGroup
    override fun dialog() = mockDialogMethodGroup
    override fun conversation() = mockConversationsMethodGroup
    override fun channel() = mockChannelsMethodGroup
    override fun im() = mockImMethodGroup
    override fun users() = mockUsersMethodGroup
    override fun oauth() = mockOauthMethodGroup
    override fun respond() = mockRespondMethodGroup
    override fun team() = mockTeamMethodGroup
    override fun usergroups() = mockUsergroupsMethodGroup
    override fun groups() = mockGroupsMethodGroup
    override fun reminders() = mockRemindersMethodGroup
}
