package io.olaph.slack.client.test.group.conversation

import io.olaph.slack.client.group.conversations.ConversationsHistoryMethod
import io.olaph.slack.client.group.conversations.ConversationsMethodGroup
import io.olaph.slack.client.group.conversations.ConversationsOpenMethod


class MockConversationMethodGroup : ConversationsMethodGroup {

    private val mockChatConversationsCreate = MockConversationsCreate()
    private val mockConversationsClose = MockConversationsClose()
    private val mockConversationsListMethod = MockConversationsListMethod()
    private val mockConversationsMembersMethod = MockConversationsMembersMethod()
    private val mockConversationsInviteMethod = MockConversationsInviteMethod()
    private val mockConversationsLeaveMethod = MockConversationsLeaveMethod()
    private val mockConversationsArchiveMethod = MockConversationsArchiveMethod()
    private val mockConversationsUnarchiveMethod = MockConversationsUnarchiveMethod()
    private val mockConversationsKickMethod = MockConversationsKickMethod()
    private val mockConversationsSetPurposeMethod = MockConversationsSetPurposeMethod()
    private val mockConversationsInfoMethod = MockConversationsInfoMethod()
    private val mockConversationsRenameMethod = MockConversationsRenameMethod()
    private val mockConversationsSetTopicMethod = MockConversationsSetTopicMethod()
    private val mockConversationsJoinMethod = MockConversationsJoinMethod()
    private val mockConversationsRepliesMethod = MockConversationsRepliesMethod()

    override fun close(authToken: String): MockConversationsClose = mockConversationsClose

    override fun create(authToken: String): MockConversationsCreate {
        return mockChatConversationsCreate
    }

    override fun history(authToken: String): ConversationsHistoryMethod {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun info(authToken: String): MockConversationsInfoMethod {
        return mockConversationsInfoMethod
    }

    override fun invite(authToken: String): MockConversationsInviteMethod {
        return mockConversationsInviteMethod
    }

    override fun join(authToken: String): MockConversationsJoinMethod {
        return mockConversationsJoinMethod
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

    override fun rename(authToken: String): MockConversationsRenameMethod {
        return mockConversationsRenameMethod
    }

    override fun replies(authToken: String): MockConversationsRepliesMethod {
        return mockConversationsRepliesMethod
    }

    override fun setPurpose(authToken: String): MockConversationsSetPurposeMethod {
        return mockConversationsSetPurposeMethod
    }

    override fun setTopic(authToken: String): MockConversationsSetTopicMethod {
        return mockConversationsSetTopicMethod
    }

    override fun unarchive(authToken: String): MockConversationsUnarchiveMethod {
        return mockConversationsUnarchiveMethod
    }

    override fun archive(authToken: String): MockConversationsArchiveMethod {
        return mockConversationsArchiveMethod
    }

}
