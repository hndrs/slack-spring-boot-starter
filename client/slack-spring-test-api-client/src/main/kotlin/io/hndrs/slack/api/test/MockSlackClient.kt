package io.hndrs.slack.api.test

import io.hndrs.slack.api.SlackClient
import io.hndrs.slack.api.test.group.auth.MockAuthGroup
import io.hndrs.slack.api.test.group.chat.MockChatMethodGroup
import io.hndrs.slack.api.test.group.conversation.MockConversationMethodGroup
import io.hndrs.slack.api.test.group.dialog.MockDialogMethodGroup
import io.hndrs.slack.api.test.group.oauth.MockOauthMethodGroup
import io.hndrs.slack.api.test.group.pins.MockPinsMethodGroup
import io.hndrs.slack.api.test.group.reminders.MockRemindersMethodGroup
import io.hndrs.slack.api.test.group.respond.MockRespondMethodGroup
import io.hndrs.slack.api.test.group.team.MockTeamMethodGroup
import io.hndrs.slack.api.test.group.usergroups.MockUsergroupsMethodGroup
import io.hndrs.slack.api.test.group.users.MockUsersMethodGroup

/**
 * [SlackClient] implementation for testing purposes
 *
 */
class MockSlackClient : io.hndrs.slack.api.SlackClient {

    private val mockRemindersMethodGroup = MockRemindersMethodGroup()
    private val mockChatGroup = MockChatMethodGroup()
    private val mockAuthGroup = MockAuthGroup()
    private val mockDialogMethodGroup = MockDialogMethodGroup()
    private val mockConversationsMethodGroup = MockConversationMethodGroup()
    private val mockUsersMethodGroup = MockUsersMethodGroup()
    private val mockOauthMethodGroup = MockOauthMethodGroup()
    private val mockRespondMethodGroup = MockRespondMethodGroup()
    private val mockTeamMethodGroup = MockTeamMethodGroup()
    private val mockUsergroupsMethodGroup = MockUsergroupsMethodGroup()
    private val mockPinsMethodGroup = MockPinsMethodGroup()

    override fun auth() = mockAuthGroup
    override fun chat() = mockChatGroup
    override fun dialog() = mockDialogMethodGroup
    override fun conversation() = mockConversationsMethodGroup
    override fun users() = mockUsersMethodGroup
    override fun oauth() = mockOauthMethodGroup
    override fun respond() = mockRespondMethodGroup
    override fun team() = mockTeamMethodGroup
    override fun usergroups() = mockUsergroupsMethodGroup
    override fun reminders() = mockRemindersMethodGroup
    override fun pins() = mockPinsMethodGroup
}
