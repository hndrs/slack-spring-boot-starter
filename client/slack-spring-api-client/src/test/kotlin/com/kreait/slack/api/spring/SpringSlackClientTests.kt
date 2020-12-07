package com.kreait.slack.api.spring

import com.kreait.slack.api.spring.group.auth.SpringAuthMethodGroup
import com.kreait.slack.api.spring.group.channels.SpringChannelsMethodGroup
import com.kreait.slack.api.spring.group.chat.SpringChatMethodGroup
import com.kreait.slack.api.spring.group.conversations.SpringConversationsMethodGroup
import com.kreait.slack.api.spring.group.dialog.SpringDialogMethodGroup
import com.kreait.slack.api.spring.group.groups.SpringGroupsMethodGroup
import com.kreait.slack.api.spring.group.oauth.SpringOauthMethodGroup
import com.kreait.slack.api.spring.group.pins.SpringPinsMethodGroup
import com.kreait.slack.api.spring.group.reminders.SpringRemindersMethodGroup
import com.kreait.slack.api.spring.group.respond.SpringRespondMethodGroup
import com.kreait.slack.api.spring.group.team.SpringTeamMethodGroup
import com.kreait.slack.api.spring.group.usergroups.SpringUsergroupMethodGroup
import com.kreait.slack.api.spring.group.users.SpringUserMethodGroup
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SpringSlackClientTests {
    private val springSlackClient = SpringSlackClient()

    @DisplayName("Check RespondMethodGroup")
    @Test
    fun testRespondGroup() {
        assert(springSlackClient.respond() is SpringRespondMethodGroup)
    }

    @DisplayName("Check AuthMethodGroup")
    @Test
    fun testAuthGroup() {
        assert(springSlackClient.auth() is SpringAuthMethodGroup)
    }

    @DisplayName("Check ChatMethodGroup")
    @Test
    fun testChatGroup() {
        assert(springSlackClient.chat() is SpringChatMethodGroup)
    }

    @DisplayName("Check DialogMethodGroup")
    @Test
    fun testDialogGroup() {
        assert(springSlackClient.dialog() is SpringDialogMethodGroup)
    }

    @DisplayName("Check ConversationMethodGroup")
    @Test
    fun testConversationGroup() {
        assert(springSlackClient.conversation() is SpringConversationsMethodGroup)
    }

    @DisplayName("Check ChannelMethodGroup")
    @Test
    fun testChannelGroup() {
        assert(springSlackClient.channel() is SpringChannelsMethodGroup)
    }

    @DisplayName("Check UsersMethodGroup")
    @Test
    fun testUsersGroup() {
        assert(springSlackClient.users() is SpringUserMethodGroup)
    }

    @DisplayName("Check OauthMethodGroup")
    @Test
    fun testOauthGroup() {
        assert(springSlackClient.oauth() is SpringOauthMethodGroup)
    }

    @DisplayName("Check TeamMethodGroup")
    @Test
    fun testTeamGroup() {
        assert(springSlackClient.team() is SpringTeamMethodGroup)
    }

    @DisplayName("Check UserGroupsMethodGroup")
    @Test
    fun testUserGroupsGroup() {
        assert(springSlackClient.usergroups() is SpringUsergroupMethodGroup)
    }

    @DisplayName("Check GroupsMethodGroup")
    @Test
    fun testGroupsGroup() {
        assert(springSlackClient.groups() is SpringGroupsMethodGroup)
    }

    @DisplayName("Check RemindersMethodGroup")
    @Test
    fun testRemindersGroup() {
        assert(springSlackClient.reminders() is SpringRemindersMethodGroup)
    }

    @DisplayName("Check PinsMethodGroup")
    @Test
    fun testPinsGroup() {
        assert(springSlackClient.pins() is SpringPinsMethodGroup)
    }
}
