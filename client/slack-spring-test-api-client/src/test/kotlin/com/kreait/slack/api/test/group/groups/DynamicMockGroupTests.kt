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
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class DynamicMockGroupTests() {


    @TestFactory
    fun methodInvocations(): List<DynamicTest> = DynamicMockGroupTests.methodInvocations(testCases = testCases())

    private val client = MockSlackClient()

    private fun testCases() = listOf(
            MockMetaInfo({ client.groups().archive("") }, mock { }, SuccessfulGroupsArchiveResponse.sample(), mock { }, ErrorGroupsArchiveResponse.sample(), GroupsArchiveRequest.sample()),
            MockMetaInfo({ client.groups().history("") }, mock { }, SuccessfulGroupsHistoryResponse.sample(), mock { }, ErrorGroupsHistoryResponse.sample(), GroupsHistoryRequest.sample()),
            MockMetaInfo({ client.groups().info("") }, mock { }, SuccessfulGroupsInfoResponse.sample(), mock { }, ErrorGroupsInfoResponse.sample(), GroupsInfoRequest.sample()),
            MockMetaInfo({ client.groups().invite("") }, mock { }, SuccessfulGroupsInviteResponse.sample(), mock { }, ErrorGroupsInviteResponse.sample(), GroupsInviteRequest.sample()),
            MockMetaInfo({ client.groups().leave("") }, mock { }, SuccessfulGroupsLeaveResponse.sample(), mock { }, ErrorGroupsLeaveResponse.sample(), GroupsLeaveRequest.sample()),
            MockMetaInfo({ client.groups().open("") }, mock { }, SuccessfulGroupsOpenResponse.sample(), mock { }, ErrorGroupsOpenResponse.sample(), GroupsOpenRequest.sample()),
            MockMetaInfo({ client.groups().rename("") }, mock { }, SuccessfulGroupsRenameResponse.sample(), mock { }, ErrorGroupsRenameResponse.sample(), GroupsRenameRequest.sample()),
            MockMetaInfo({ client.groups().setPurpose("") }, mock { }, SuccessfulGroupsSetPurposeResponse.sample(), mock { }, ErrorGroupsSetPurposeResponse.sample(), GroupsSetPurposeRequest.sample()),
            MockMetaInfo({ client.groups().setTopic("") }, mock { }, SuccessfulGroupsSetTopicResponse.sample(), mock { }, ErrorGroupsSetTopicResponse.sample(), GroupsSetTopicRequest.sample()),
            MockMetaInfo({ client.groups().create("") }, mock { }, SuccessfulGroupsCreateResponse.sample(), mock { }, ErrorGroupsCreateResponse.sample(), GroupsCreateRequest.sample()),
            MockMetaInfo({ client.groups().createChild("") }, mock { }, SuccessfulGroupsCreateChildResponse.sample(), mock { }, ErrorGroupsCreateChildResponse.sample(), GroupsCreateChildRequest.sample()),
            MockMetaInfo({ client.groups().kick("") }, mock { }, SuccessfulGroupsKickResponse.sample(), mock { }, ErrorGroupsKickResponse.sample(), GroupsKickRequest.sample()),
            MockMetaInfo({ client.groups().list("") }, mock { }, SuccessfulGroupsListResponse.sample(), mock { }, ErrorGroupsListResponse.sample(), GroupsListRequest.sample()),
            MockMetaInfo({ client.groups().replies("") }, mock { }, SuccessfulGroupsRepliesResponse.sample(), mock { }, ErrorGroupsRepliesResponse.sample(), GroupsRepliesRequest.sample()),
            MockMetaInfo({ client.groups().mark("") }, mock { }, SuccessfulGroupsMarkResponse.sample(), mock { }, ErrorGroupsMarkResponse.sample(), GroupsMarkRequest.sample()),
            MockMetaInfo({ client.groups().unarchive("") }, mock { }, SuccessfulGroupsUnarchiveResponse.sample(), mock { }, ErrorGroupsUnarchiveResponse.sample(), GroupsUnarchiveRequest.sample())
    )
}
