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
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class DynamicMockConversationTests() {


    @TestFactory
    fun methodInvocations(): List<DynamicTest> = DynamicMockGroupTests.methodInvocations(testCases = testCases())

    private val client = MockSlackClient()

    private fun testCases() = listOf(
            MockMetaInfo({ client.conversation().archive("") }, mock { }, SuccessfulConversationArchiveResponse.sample(), mock { }, ErrorConversationArchiveResponse.sample(), ConversationArchiveRequest.sample()),
            MockMetaInfo({ client.conversation().close("") }, mock { }, SuccessfulConversationCloseResponse.sample(), mock { }, ErrorConversationCloseResponse.sample(), ConversationCloseRequest.sample()),
            MockMetaInfo({ client.conversation().history("") }, mock { }, SuccessfulConversationHistoryResponse.sample(), mock { }, ErrorConversationHistoryResponse.sample(), ConversationsHistoryRequest.sample()),
            MockMetaInfo({ client.conversation().info("") }, mock { }, SuccessfulConversationsInfoResponse.sample(), mock { }, ErrorConversationsInfoResponse.sample(), ConversationsInfoRequest.sample()),
            MockMetaInfo({ client.conversation().invite("") }, mock { }, SuccessfulConversationInviteResponse.sample(), mock { }, ErrorConversationInviteResponse.sample(), ConversationsInviteRequest.sample()),
            MockMetaInfo({ client.conversation().join("") }, mock { }, SuccessfulConversationJoinResponse.sample(), mock { }, ErrorConversationJoinResponse.sample(), ConversationJoinRequest.sample()),
            MockMetaInfo({ client.conversation().leave("") }, mock { }, SuccessfulConversationLeaveResponse.sample(), mock { }, ErrorConversationLeaveResponse.sample(), ConversationsLeaveRequest.sample()),
            MockMetaInfo({ client.conversation().open("") }, mock { }, SuccessfulConversationOpenResponse.sample(), mock { }, ErrorConversationOpenResponse.sample(), ConversationsOpenRequest.sample()),
            MockMetaInfo({ client.conversation().rename("") }, mock { }, SuccessfulConversationsRenameResponse.sample(), mock { }, ErrorConversationsRenameResponse.sample(), ConversationsRenameRequest.sample()),
            MockMetaInfo({ client.conversation().setPurpose("") }, mock { }, SuccessfulConversationSetPurposeResponse.sample(), mock { }, ErrorConversationSetPurposeResponse.sample(), ConversationsSetPurposeRequest.sample()),
            MockMetaInfo({ client.conversation().setTopic("") }, mock { }, SuccessfulConversationSetTopicResponse.sample(), mock { }, ErrorConversationSetTopicResponse.sample(), ConversationsSetTopicRequest.sample()),
            MockMetaInfo({ client.conversation().create("") }, mock { }, SuccessfulConversationCreateResponse.sample(), mock { }, ErrorConversationCreateResponse.sample(), ConversationCreateRequest.sample()),
            MockMetaInfo({ client.conversation().kick("") }, mock { }, SuccessfulConversationKickResponse.sample(), mock { }, ErrorConversationKickResponse.sample(), ConversationsKickRequest.sample()),
            MockMetaInfo({ client.conversation().list("") }, mock { }, SuccessfulConversationListResponse.sample(), mock { }, ErrorConversationListResponse.sample(), ConversationsListRequest.sample()),
            MockMetaInfo({ client.conversation().members("") }, mock { }, SuccessfulConversationMembersResponse.sample(), mock { }, ErrorConversationMembersResponse.sample(), ConversationMembersRequest.sample()),
            MockMetaInfo({ client.conversation().replies("") }, mock { }, SuccessfulConversationRepliesResponse.sample(), mock { }, ErrorConversationRepliesResponse.sample(), ConversationsRepliesRequest.sample()),
            MockMetaInfo({ client.conversation().unarchive("") }, mock { }, SuccessfulConversationUnarchiveResponse.sample(), mock { }, ErrorConversationUnarchiveResponse.sample(), ConversationUnarchiveRequest.sample())
    )
}
