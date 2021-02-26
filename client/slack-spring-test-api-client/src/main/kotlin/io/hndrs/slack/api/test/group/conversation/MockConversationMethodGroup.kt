package io.hndrs.slack.api.test.group.conversation

import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup

/**
 * Testable implementation of [ConversationsMethodGroup]
 */
class MockConversationMethodGroup : io.hndrs.slack.api.group.conversations.ConversationsMethodGroup {

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

    override fun close(authToken: String) = mockConversationsClose
    override fun create(authToken: String) = mockConversationsCreate
    override fun history(authToken: String) = mockConversationsHistoryMethod
    override fun info(authToken: String) = mockConversationsInfoMethod
    override fun invite(authToken: String) = mockConversationsInviteMethod
    override fun join(authToken: String) = mockConversationsJoinMethod
    override fun kick(authToken: String) = mockConversationsKickMethod
    override fun leave(authToken: String) = mockConversationsLeaveMethod
    override fun list(authToken: String) = mockConversationsListMethod
    override fun members(authToken: String) = mockConversationsMembersMethod
    override fun open(authToken: String) = mockConversationsOpenMethod
    override fun rename(authToken: String) = mockConversationsRenameMethod
    override fun replies(authToken: String) = mockConversationsRepliesMethod
    override fun setPurpose(authToken: String) = mockConversationsSetPurposeMethod
    override fun setTopic(authToken: String) = mockConversationsSetTopicMethod
    override fun unarchive(authToken: String) = mockConversationsUnarchiveMethod
    override fun archive(authToken: String) = mockConversationsArchiveMethod
}
