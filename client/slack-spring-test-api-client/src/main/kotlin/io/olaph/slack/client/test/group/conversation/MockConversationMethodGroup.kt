package io.olaph.slack.client.test.group.conversation

import io.olaph.slack.client.group.conversations.ConversationsMethodGroup

class MockConversationMethodGroup : ConversationsMethodGroup {

    private val mockConversationsCreate = MockConversationsCreate()
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
    private val mockConversationsHistoryMethod = MockConversationsHistoryMethod()
    private val mockConversationsOpenMethod = MockConversationsOpenMethod()

    override fun close(authToken: String): MockConversationsClose = mockConversationsClose

    override fun create(authToken: String): MockConversationsCreate {
        return mockConversationsCreate
    }

    override fun history(authToken: String): MockConversationsHistoryMethod {
        return mockConversationsHistoryMethod
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

    override fun open(authToken: String): MockConversationsOpenMethod {
        return mockConversationsOpenMethod
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
