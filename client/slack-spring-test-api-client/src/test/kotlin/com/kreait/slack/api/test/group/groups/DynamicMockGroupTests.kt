package com.kreait.slack.api.test.group.groups

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
import com.kreait.slack.api.contract.jackson.group.groups.GroupsSetPurposeRequest
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
import com.kreait.slack.api.test.DynamicMockGroupTests
import com.kreait.slack.api.test.MockMetaInfo
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class DynamicMockGroupTests() {


    @TestFactory
    fun methodInvocations(): List<DynamicTest> = DynamicMockGroupTests.methodInvocations(testCases = testCases())

    private fun testCases() = listOf(
            MockMetaInfo(MockGroupsArchiveMethod(), mock { }, SuccessfulGroupsArchiveResponse.sample(), mock { }, ErrorGroupsArchiveResponse.sample(), GroupsArchiveRequest.sample()),
            MockMetaInfo(MockGroupsHistoryMethod(), mock { }, SuccessfulGroupsHistoryResponse.sample(), mock { }, ErrorGroupsHistoryResponse.sample(), GroupsHistoryRequest.sample()),
            MockMetaInfo(MockGroupsInfoMethod(), mock { }, SuccessfulGroupsInfoResponse.sample(), mock { }, ErrorGroupsInfoResponse.sample(), GroupsInfoRequest.sample()),
            MockMetaInfo(MockGroupsInviteMethod(), mock { }, SuccessfulGroupsInviteResponse.sample(), mock { }, ErrorGroupsInviteResponse.sample(), GroupsInviteRequest.sample()),
            MockMetaInfo(MockGroupsLeaveMethod(), mock { }, SuccessfulGroupsLeaveResponse.sample(), mock { }, ErrorGroupsLeaveResponse.sample(), GroupsLeaveRequest.sample()),
            MockMetaInfo(MockGroupsOpenMethod(), mock { }, SuccessfulGroupsOpenResponse.sample(), mock { }, ErrorGroupsOpenResponse.sample(), GroupsOpenRequest.sample()),
            MockMetaInfo(MockGroupsRenameMethod(), mock { }, SuccessfulGroupsRenameResponse.sample(), mock { }, ErrorGroupsRenameResponse.sample(), GroupsRenameRequest.sample()),
            MockMetaInfo(MockGroupsSetPurposeMethod(), mock { }, SuccessfulGroupsSetPurposeResponse.sample(), mock { }, ErrorGroupsSetPurposeResponse.sample(), GroupsSetPurposeRequest.sample()),
            MockMetaInfo(MockGroupsSetTopicMethod(), mock { }, SuccessfulGroupsSetTopicResponse.sample(), mock { }, ErrorGroupsSetTopicResponse.sample(), GroupsSetTopicRequest.sample()),
            MockMetaInfo(MockGroupsCreateMethod(), mock { }, SuccessfulGroupsCreateResponse.sample(), mock { }, ErrorGroupsCreateResponse.sample(), GroupsCreateRequest.sample()),
            MockMetaInfo(MockGroupsCreateChildMethod(), mock { }, SuccessfulGroupsCreateChildResponse.sample(), mock { }, ErrorGroupsCreateChildResponse.sample(), GroupsCreateChildRequest.sample()),
            MockMetaInfo(MockGroupsKickMethod(), mock { }, SuccessfulGroupsKickResponse.sample(), mock { }, ErrorGroupsKickResponse.sample(), GroupsKickRequest.sample()),
            MockMetaInfo(MockGroupsListMethod(), mock { }, SuccessfulGroupsListResponse.sample(), mock { }, ErrorGroupsListResponse.sample(), GroupsListRequest.sample()),
            MockMetaInfo(MockGroupsRepliesMethod(), mock { }, SuccessfulGroupsRepliesResponse.sample(), mock { }, ErrorGroupsRepliesResponse.sample(), GroupsRepliesRequest.sample()),
            MockMetaInfo(MockGroupsMarkMethod(), mock { }, SuccessfulGroupsMarkResponse.sample(), mock { }, ErrorGroupsMarkResponse.sample(), GroupsMarkRequest.sample()),
            MockMetaInfo(MockGroupsUnarchiveMethod(), mock { }, SuccessfulGroupsUnarchiveResponse.sample(), mock { }, ErrorGroupsUnarchiveResponse.sample(), GroupsUnarchiveRequest.sample())
    )
}