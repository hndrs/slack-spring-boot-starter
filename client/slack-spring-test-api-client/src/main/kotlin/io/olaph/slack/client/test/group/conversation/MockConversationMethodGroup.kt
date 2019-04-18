package io.olaph.slack.client.test.group.conversation

import io.olaph.slack.client.group.conversations.ConversationsHistoryMethod
import io.olaph.slack.client.group.conversations.ConversationsInfoMethod
import io.olaph.slack.client.group.conversations.ConversationsJoinMethod
import io.olaph.slack.client.group.conversations.ConversationsKickMethod
import io.olaph.slack.client.group.conversations.ConversationsMethodGroup
import io.olaph.slack.client.group.conversations.ConversationsOpenMethod
import io.olaph.slack.client.group.conversations.ConversationsRenameMethod
import io.olaph.slack.client.group.conversations.ConversationsRepliesMethod
import io.olaph.slack.client.group.conversations.ConversationsSetPurposeMethod
import io.olaph.slack.client.group.conversations.ConversationsSetTopicMethod
import io.olaph.slack.client.group.conversations.ConversationsUnarchiveMethod


class MockConversationMethodGroup : ConversationsMethodGroup {

    private val mockChatConversationsCreate = MockConversationsCreate()
    private val mockConversationsClose = MockConversationsClose()
    private val mockConversationsListMethod = MockConversationsListMethod()
    private val mockConversationsMembersMethod = MockConversationsMembersMethod()
    private val mockConversationsInviteMethod = MockConversationsInviteMethod()
    private val mockConversationsLeaveMethod = MockConversationsLeaveMethod()
    private val mockConversationsArchiveMethod = MockConversationsArchiveMethod()
    private val mockConversationsKickMethod = MockConversationsKickMethod()

    override fun close(authToken: String): MockConversationsClose = mockConversationsClose

    override fun create(authToken: String): MockConversationsCreate {
        return mockChatConversationsCreate
    }

    override fun history(authToken: String): ConversationsHistoryMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun info(authToken: String): ConversationsInfoMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun invite(authToken: String): MockConversationsInviteMethod {
        return mockConversationsInviteMethod
    }

    override fun join(authToken: String): ConversationsJoinMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun kick(authToken: String): MockConversationsKickMethod {
        return mockConversationsKickMethod
    }

    override fun leave(authToken: String): MockConversationsLeaveMethod {
        return mockConversationsLeaveMethod
    }

    override fun list(authToken: String): MockConversationsListMethod {
        return mockConversationsListMethod
    }

    override fun members(authToken: String): MockConversationsMembersMethod {
        return mockConversationsMembersMethod
    }

    override fun open(authToken: String): ConversationsOpenMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun rename(authToken: String): ConversationsRenameMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun replies(authToken: String): ConversationsRepliesMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPurpose(authToken: String): ConversationsSetPurposeMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setTopic(authToken: String): ConversationsSetTopicMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unarchive(authToken: String): ConversationsUnarchiveMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun archive(authToken: String): MockConversationsArchiveMethod {
        return mockConversationsArchiveMethod;
    }

}
