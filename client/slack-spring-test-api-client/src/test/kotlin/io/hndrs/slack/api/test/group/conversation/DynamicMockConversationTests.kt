package io.hndrs.slack.api.test.group.conversation

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationArchiveRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationCloseRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationCreateRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationJoinRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationMembersRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationUnarchiveRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsHistoryRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsInfoRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsInviteRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsKickRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsLeaveRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsListRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsOpenRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsRenameRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsRepliesRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsSetPurposeRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsSetTopicRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationArchiveResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationCloseResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationCreateResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationHistoryResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationInviteResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationJoinResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationKickResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationLeaveResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationListResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationMembersResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationOpenResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationRepliesResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationSetPurposeResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationSetTopicResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationUnarchiveResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationsInfoResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationsRenameResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationArchiveResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationCloseResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationCreateResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationHistoryResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationInviteResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationJoinResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationKickResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationLeaveResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationListResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationMembersResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationOpenResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationRepliesResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationSetPurposeResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationSetTopicResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationUnarchiveResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationsInfoResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationsRenameResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.sample
import io.hndrs.slack.api.test.DynamicMockGroupTests
import io.hndrs.slack.api.test.MockMetaInfo
import io.hndrs.slack.api.test.MockSlackClient
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
