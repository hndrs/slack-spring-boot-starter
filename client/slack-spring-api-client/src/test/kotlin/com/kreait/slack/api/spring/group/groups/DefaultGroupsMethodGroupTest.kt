package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsArchiveResponse
import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsCreateChildResponse
import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsCreateResponse
import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsHistoryResponse
import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsInfoResponse
import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsInviteResponse
import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsKickResponse
import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsLeaveResponse
import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsListResponse
import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsMarkResponse
import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsOpenResponse
import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsRenameResponse
import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsRepliesResponse
import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsSetTopicResponse
import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsArchiveRequest
import com.kreait.slack.api.contract.jackson.group.groups.GroupsCreateChildRequest
import com.kreait.slack.api.contract.jackson.group.groups.GroupsCreateRequest
import com.kreait.slack.api.contract.jackson.group.groups.GroupsHistoryRequest
import com.kreait.slack.api.contract.jackson.group.groups.GroupsInfoRequest
import com.kreait.slack.api.contract.jackson.group.groups.GroupsInviteRequest
import com.kreait.slack.api.contract.jackson.group.groups.GroupsKickRequest
import com.kreait.slack.api.contract.jackson.group.groups.GroupsLeaveRequest
import com.kreait.slack.api.contract.jackson.group.groups.GroupsListRequest
import com.kreait.slack.api.contract.jackson.group.groups.GroupsMarkRequest
import com.kreait.slack.api.contract.jackson.group.groups.GroupsOpenRequest
import com.kreait.slack.api.contract.jackson.group.groups.GroupsRenameRequest
import com.kreait.slack.api.contract.jackson.group.groups.GroupsRepliesRequest
import com.kreait.slack.api.contract.jackson.group.groups.GroupsSetTopicRequest
import com.kreait.slack.api.contract.jackson.group.groups.GroupsUnarchiveRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsArchiveResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsCreateChildResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsCreateResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsHistoryResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsInfoResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsInviteResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsKickResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsLeaveResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsListResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsMarkResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsOpenResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsRenameResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsRepliesResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsSetTopicResponse
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.contract.jackson.group.users.GetPresenceRequest
import com.kreait.slack.api.contract.jackson.group.users.sample
import com.kreait.slack.api.spring.DynamicGroupTests
import com.kreait.slack.api.spring.MetaInfo
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.springframework.web.client.RestTemplate

@DisplayName("test Groups methodgroup")
class DefaultGroupsMethodGroupTest() {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @TestFactory
    fun methodInvocations(): List<DynamicTest> = DynamicGroupTests.methodInvocations(testCases = testCases(), mockTemplate = mockTemplate)

    private fun testCases() = listOf(
            MetaInfo("groups.archive", SuccessfulGroupsArchiveResponse.sample(), ErrorGroupsArchiveResponse.sample(), GroupsArchiveRequest.sample(), DefaultGroupsArchiveMethod("", mockTemplate)),
            MetaInfo("groups.createChild", SuccessfulGroupsCreateChildResponse.sample(), ErrorGroupsCreateChildResponse.sample(), GroupsCreateChildRequest.sample(), DefaultGroupsCreateChildMethod("", mockTemplate)),
            MetaInfo("groups.create", SuccessfulGroupsCreateResponse.sample(), ErrorGroupsCreateResponse.sample(), GroupsCreateRequest, DefaultGroupsCreateMethod("", mockTemplate)),
            MetaInfo("groups.history", SuccessfulGroupsHistoryResponse.sample(), ErrorGroupsHistoryResponse.sample(), GroupsHistoryRequest.sample(), DefaultGroupsHistoryMethod("", mockTemplate)),
            MetaInfo("groups.info", SuccessfulGroupsInfoResponse.sample(), ErrorGroupsInfoResponse.sample(), GroupsInfoRequest.sample(), DefaultGroupsInfoMethod("", mockTemplate)),
            MetaInfo("groups.open", SuccessfulGroupsOpenResponse.sample(), ErrorGroupsOpenResponse.sample(), GroupsOpenRequest.sample(), DefaultGroupsOpenMethod("", mockTemplate)),
            MetaInfo("groups.invite", SuccessfulGroupsInviteResponse.sample(), ErrorGroupsInviteResponse.sample(), GroupsInviteRequest, DefaultGroupsInviteMethod("", mockTemplate)),
            MetaInfo("groups.kick", SuccessfulGroupsKickResponse.sample(), ErrorGroupsKickResponse.sample(), GroupsKickRequest.sample(), DefaultGroupsKickMethod("", mockTemplate)),
            MetaInfo("groups.leave", SuccessfulGroupsLeaveResponse.sample(), ErrorGroupsLeaveResponse.sample(), GroupsLeaveRequest.sample(), DefaultGroupsLeaveMethod("", mockTemplate)),
            MetaInfo("groups.list", SuccessfulGroupsListResponse.sample(), ErrorGroupsListResponse.sample(), GroupsListRequest.sample(), DefaultGroupsListMethod("", mockTemplate)),
            MetaInfo("groups.mark", SuccessfulGroupsMarkResponse.sample(), ErrorGroupsMarkResponse.sample(), GroupsMarkRequest.sample(), DefaultGroupsMarkMethod("", mockTemplate)),
            MetaInfo("groups.rename", SuccessfulGroupsRenameResponse.sample(), ErrorGroupsRenameResponse.sample(), GroupsRenameRequest.sample(), DefaultGroupsRenameMethod("", mockTemplate)),
            MetaInfo("groups.replies", SuccessfulGroupsRepliesResponse.sample(), ErrorGroupsRepliesResponse.sample(), GroupsRepliesRequest.sample(), DefaultGroupsRepliesMethod("", mockTemplate)),
            MetaInfo("groups.setPurpose", SuccessfulGroupsSetPurposeResponse.sample(), ErrorGroupsSetPurposeResponse.sample(), GetPresenceRequest.sample(), DefaultGroupsSetPurposeMethod("", mockTemplate)),
            MetaInfo("groups.setTopic", SuccessfulGroupsSetTopicResponse.sample(), ErrorGroupsSetTopicResponse.sample(), GroupsSetTopicRequest.sample(), DefaultGroupsSetTopicMethod("", mockTemplate)),
            MetaInfo("groups.unarchive", SuccessfulGroupsUnarchiveResponse.sample(), ErrorGroupsUnarchiveResponse.sample(), GroupsUnarchiveRequest.sample(), DefaultGroupsUnarchiveMethod("", mockTemplate))
    )
}