package io.hndrs.slack.api.spring.group.conversations

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationArchiveRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationCloseRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationCreateRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationJoinRequest
import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationMembersRequest
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
import io.hndrs.slack.api.spring.DynamicGroupTests
import io.hndrs.slack.api.spring.MetaInfo
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.springframework.web.client.RestTemplate


@DisplayName("Conversations MethodGroup")
class MethodInvocationTest {

    protected lateinit var mockTemplate: RestTemplate


    @BeforeEach
    fun setup() {
        mockTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
    }

    @TestFactory
    @DisplayName("Method Invocation Tests")
    fun methodInvocations(): List<DynamicTest> {
        return DynamicGroupTests.methodInvocations(testCases(), mockTemplate)
    }

    private fun testCases() = listOf(
            MetaInfo("conversations.archive", SuccessfulConversationArchiveResponse.sample(), ErrorConversationArchiveResponse.sample(), ConversationArchiveRequest.sample(), SpringConversationsArchiveMethod("", mockTemplate)),
            MetaInfo("conversations.close", SuccessfulConversationCloseResponse.sample(), ErrorConversationCloseResponse.sample(), ConversationCloseRequest.sample(), SpringConversationsCloseMethod("", mockTemplate)),
            MetaInfo("conversations.create", SuccessfulConversationCreateResponse.sample(), ErrorConversationCreateResponse.sample(), ConversationCreateRequest.sample(), SpringConversationsCreateMethod("", mockTemplate)),
            MetaInfo("conversations.history", SuccessfulConversationHistoryResponse.sample(), ErrorConversationHistoryResponse.sample(), ConversationsHistoryRequest.sample(), SpringConversationsHistoryMethod("", mockTemplate)),
            MetaInfo("conversations.leave", SuccessfulConversationLeaveResponse.sample(), ErrorConversationLeaveResponse.sample(), ConversationsLeaveRequest.sample(), SpringConversationsLeaveMethod("", mockTemplate)),
            MetaInfo("conversations.list", SuccessfulConversationListResponse.sample(), ErrorConversationListResponse.sample(), ConversationsListRequest.sample(), SpringConversationsListMethod("", mockTemplate)),
            MetaInfo("conversations.members", SuccessfulConversationMembersResponse.sample(), ErrorConversationMembersResponse.sample(), ConversationMembersRequest.sample(), SpringConversationsMembersMethod("", mockTemplate)),
            MetaInfo("conversations.open", SuccessfulConversationOpenResponse.sample(), ErrorConversationOpenResponse.sample(), ConversationsOpenRequest.sample(), SpringConversationsOpenMethod("", mockTemplate)),
            MetaInfo("conversations.rename", SuccessfulConversationsRenameResponse.sample(), ErrorConversationsRenameResponse.sample(), ConversationsRenameRequest.sample(), SpringConversationsRenameMethod("", mockTemplate)),
            MetaInfo("conversations.replies", SuccessfulConversationRepliesResponse.sample(), ErrorConversationRepliesResponse.sample(), ConversationsRepliesRequest.sample(), SpringConversationsRepliesMethod("", mockTemplate)),
            MetaInfo("conversations.setPurpose", SuccessfulConversationSetPurposeResponse.sample(), ErrorConversationSetPurposeResponse.sample(), ConversationsSetPurposeRequest.sample(), SpringConversationsSetPurposeMethod("", mockTemplate)),
            MetaInfo("conversations.setTopic", SuccessfulConversationSetTopicResponse.sample(), ErrorConversationSetTopicResponse.sample(), ConversationsSetTopicRequest.sample(), SpringConversationsSetTopicMethod("", mockTemplate)),
            MetaInfo("conversations.info", SuccessfulConversationsInfoResponse.sample(), ErrorConversationsInfoResponse.sample(), ConversationsInfoRequest.sample(), SpringConversationsInfoMethod("", mockTemplate)),
            MetaInfo("conversations.invite", SuccessfulConversationInviteResponse.sample(), ErrorConversationInviteResponse.sample(), ConversationsInviteRequest.sample(), SpringConversationsInviteMethod("", mockTemplate)),
            MetaInfo("conversations.join", SuccessfulConversationJoinResponse.sample(), ErrorConversationJoinResponse.sample(), ConversationJoinRequest.sample(), SpringConversationsJoinMethod("", mockTemplate)),
            MetaInfo("conversations.kick", SuccessfulConversationKickResponse.sample(), ErrorConversationKickResponse.sample(), ConversationsKickRequest.sample(), SpringConversationsKickMethod("", mockTemplate)),
            MetaInfo("conversations.unarchive", SuccessfulConversationUnarchiveResponse.sample(), ErrorConversationUnarchiveResponse.sample(), ConversationArchiveRequest.sample(), SpringConversationsUnarchiveMethod("", mockTemplate))
    )


}
