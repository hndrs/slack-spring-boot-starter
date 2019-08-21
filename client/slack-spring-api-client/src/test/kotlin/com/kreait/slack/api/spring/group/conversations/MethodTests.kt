package com.kreait.slack.api.spring.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationArchiveRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationCloseRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationCreateRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationJoinRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationMembersRequest
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
import com.kreait.slack.api.spring.DynamicGroupTests
import com.kreait.slack.api.spring.MetaInfo
import com.kreait.slack.api.spring.group.RestTemplateFactory
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
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @TestFactory
    @DisplayName("Method Invocation Tests")
    fun methodInvocations(): List<DynamicTest> {
        return DynamicGroupTests.methodInvocations(testCases(), mockTemplate)
    }

    private fun testCases() = listOf(
            MetaInfo("conversations.archive", SuccessfulConversationArchiveResponse.sample(), ErrorConversationArchiveResponse.sample(), ConversationArchiveRequest.sample(), DefaultConversationsArchiveMethod("", mockTemplate)),
            MetaInfo("conversations.close", SuccessfulConversationCloseResponse.sample(), ErrorConversationCloseResponse.sample(), ConversationCloseRequest.sample(), DefaultConversationsCloseMethod("", mockTemplate)),
            MetaInfo("conversations.create", SuccessfulConversationCreateResponse.sample(), ErrorConversationCreateResponse.sample(), ConversationCreateRequest.sample(), DefaultConversationsCreateMethod("", mockTemplate)),
            MetaInfo("conversations.history", SuccessfulConversationHistoryResponse.sample(), ErrorConversationHistoryResponse.sample(), ConversationsHistoryRequest.sample(), DefaultConversationsHistoryMethod("", mockTemplate)),
            MetaInfo("conversations.leave", SuccessfulConversationLeaveResponse.sample(), ErrorConversationLeaveResponse.sample(), ConversationsLeaveRequest.sample(), DefaultConversationsLeaveMethod("", mockTemplate)),
            MetaInfo("conversations.list", SuccessfulConversationListResponse.sample(), ErrorConversationListResponse.sample(), ConversationsListRequest.sample(), DefaultConversationsListMethod("", mockTemplate)),
            MetaInfo("conversations.members", SuccessfulConversationMembersResponse.sample(), ErrorConversationMembersResponse.sample(), ConversationMembersRequest.sample(), DefaultConversationsMembersMethod("", mockTemplate)),
            MetaInfo("conversations.open", SuccessfulConversationOpenResponse.sample(), ErrorConversationOpenResponse.sample(), ConversationsOpenRequest.sample(), DefaultConversationsOpenMethod("", mockTemplate)),
            MetaInfo("conversations.rename", SuccessfulConversationsRenameResponse.sample(), ErrorConversationsRenameResponse.sample(), ConversationsRenameRequest.sample(), DefaultConversationsRenameMethod("", mockTemplate)),
            MetaInfo("conversations.replies", SuccessfulConversationRepliesResponse.sample(), ErrorConversationRepliesResponse.sample(), ConversationsRepliesRequest.sample(), DefaultConversationsRepliesMethod("", mockTemplate)),
            MetaInfo("conversations.setPurpose", SuccessfulConversationSetPurposeResponse.sample(), ErrorConversationSetPurposeResponse.sample(), ConversationsSetPurposeRequest.sample(), DefaultConversationsSetPurposeMethod("", mockTemplate)),
            MetaInfo("conversations.setTopic", SuccessfulConversationSetTopicResponse.sample(), ErrorConversationSetTopicResponse.sample(), ConversationsSetTopicRequest.sample(), DefaultConversationsSetTopicMethod("", mockTemplate)),
            MetaInfo("conversations.info", SuccessfulConversationsInfoResponse.sample(), ErrorConversationsInfoResponse.sample(), ConversationsInfoRequest.sample(), DefaultConversationsInfoMethod("", mockTemplate)),
            MetaInfo("conversations.invite", SuccessfulConversationInviteResponse.sample(), ErrorConversationInviteResponse.sample(), ConversationsInviteRequest.sample(), DefaultConversationsInviteMethod("", mockTemplate)),
            MetaInfo("conversations.join", SuccessfulConversationJoinResponse.sample(), ErrorConversationJoinResponse.sample(), ConversationJoinRequest.sample(), DefaultConversationsJoinMethod("", mockTemplate)),
            MetaInfo("conversations.kick", SuccessfulConversationKickResponse.sample(), ErrorConversationKickResponse.sample(), ConversationsKickRequest.sample(), DefaultConversationsKickMethod("", mockTemplate)),
            MetaInfo("conversations.unarchive", SuccessfulConversationUnarchiveResponse.sample(), ErrorConversationUnarchiveResponse.sample(), ConversationArchiveRequest.sample(), DefaultConversationsUnarchiveMethod("", mockTemplate))
    )


}
