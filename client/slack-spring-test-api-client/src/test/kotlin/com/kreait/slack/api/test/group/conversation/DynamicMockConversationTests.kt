package com.kreait.slack.api.test.group.conversation

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationArchiveRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationCloseRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationCreateRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationJoinRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationMembersRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationUnarchiveRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsHistoryRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsInfoRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsInviteRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsKickRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsLeaveRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsListRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsOpenRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsRenameRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsRepliesRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsSetPurposeRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsSetTopicRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationArchiveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationCloseResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationCreateResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationHistoryResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationInviteResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationJoinResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationKickResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationLeaveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationListResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationMembersResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationOpenResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationRepliesResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationSetTopicResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationsInfoResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationsRenameResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationArchiveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationCloseResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationCreateResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationHistoryResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationInviteResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationJoinResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationKickResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationLeaveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationListResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationMembersResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationOpenResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationRepliesResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationSetTopicResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationsInfoResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationsRenameResponse
import com.kreait.slack.api.contract.jackson.group.conversations.sample
import com.kreait.slack.api.test.DynamicMockGroupTests
import com.kreait.slack.api.test.MockMetaInfo
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class DynamicMockConversationTests() {


    @TestFactory
    fun methodInvocations(): List<DynamicTest> = DynamicMockGroupTests.methodInvocations(testCases = testCases())

    private fun testCases() = listOf(
            MockMetaInfo(MockConversationsArchiveMethod(), mock { }, SuccessfulConversationArchiveResponse.sample(), mock { }, ErrorConversationArchiveResponse.sample(), ConversationArchiveRequest.sample()),
            MockMetaInfo(MockConversationsClose(), mock { }, SuccessfulConversationCloseResponse.sample(), mock { }, ErrorConversationCloseResponse.sample(), ConversationCloseRequest.sample()),
            MockMetaInfo(MockConversationsHistoryMethod(), mock { }, SuccessfulConversationHistoryResponse.sample(), mock { }, ErrorConversationHistoryResponse.sample(), ConversationsHistoryRequest.sample()),
            MockMetaInfo(MockConversationsInfoMethod(), mock { }, SuccessfulConversationsInfoResponse.sample(), mock { }, ErrorConversationsInfoResponse.sample(), ConversationsInfoRequest.sample()),
            MockMetaInfo(MockConversationsInviteMethod(), mock { }, SuccessfulConversationInviteResponse.sample(), mock { }, ErrorConversationInviteResponse.sample(), ConversationsInviteRequest.sample()),
            MockMetaInfo(MockConversationsJoinMethod(), mock { }, SuccessfulConversationJoinResponse.sample(), mock { }, ErrorConversationJoinResponse.sample(), ConversationJoinRequest.sample()),
            MockMetaInfo(MockConversationsLeaveMethod(), mock { }, SuccessfulConversationLeaveResponse.sample(), mock { }, ErrorConversationLeaveResponse.sample(), ConversationsLeaveRequest.sample()),
            MockMetaInfo(MockConversationsOpenMethod(), mock { }, SuccessfulConversationOpenResponse.sample(), mock { }, ErrorConversationOpenResponse.sample(), ConversationsOpenRequest.sample()),
            MockMetaInfo(MockConversationsRenameMethod(), mock { }, SuccessfulConversationsRenameResponse.sample(), mock { }, ErrorConversationsRenameResponse.sample(), ConversationsRenameRequest.sample()),
            MockMetaInfo(MockConversationsSetPurposeMethod(), mock { }, SuccessfulConversationSetPurposeResponse.sample(), mock { }, ErrorConversationSetPurposeResponse.sample(), ConversationsSetPurposeRequest.sample()),
            MockMetaInfo(MockConversationsSetTopicMethod(), mock { }, SuccessfulConversationSetTopicResponse.sample(), mock { }, ErrorConversationSetTopicResponse.sample(), ConversationsSetTopicRequest.sample()),
            MockMetaInfo(MockConversationsCreate(), mock { }, SuccessfulConversationCreateResponse.sample(), mock { }, ErrorConversationCreateResponse.sample(), ConversationCreateRequest.sample()),
            MockMetaInfo(MockConversationsKickMethod(), mock { }, SuccessfulConversationKickResponse.sample(), mock { }, ErrorConversationKickResponse.sample(), ConversationsKickRequest.sample()),
            MockMetaInfo(MockConversationsListMethod(), mock { }, SuccessfulConversationListResponse.sample(), mock { }, ErrorConversationListResponse.sample(), ConversationsListRequest.sample()),
            MockMetaInfo(MockConversationsMembersMethod(), mock { }, SuccessfulConversationMembersResponse.sample(), mock { }, ErrorConversationMembersResponse.sample(), ConversationMembersRequest.sample()),
            MockMetaInfo(MockConversationsRepliesMethod(), mock { }, SuccessfulConversationRepliesResponse.sample(), mock { }, ErrorConversationRepliesResponse.sample(), ConversationsRepliesRequest.sample()),
            MockMetaInfo(MockConversationsUnarchiveMethod(), mock { }, SuccessfulConversationUnarchiveResponse.sample(), mock { }, ErrorConversationUnarchiveResponse.sample(), ConversationUnarchiveRequest.sample())
    )
}