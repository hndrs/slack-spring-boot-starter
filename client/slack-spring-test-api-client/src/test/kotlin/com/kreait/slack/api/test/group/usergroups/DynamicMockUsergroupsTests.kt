package com.kreait.slack.api.test.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.CreateRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.DisableRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.EnableRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorCreateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorDisableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorEnableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorListResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.ListRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulCreateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulDisableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulEnableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulListResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.UpdateRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.sample
import com.kreait.slack.api.contract.jackson.group.usergroups.users.ErrorUsergroupUsersUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.users.ErrorUsergroupsUsersListResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.users.SuccessfulUsergroupUsersUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.users.SuccessfulUsergroupsUsersListResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.users.UsergroupUsersUpdateRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.users.UsergroupsUsersListRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.users.sample
import com.kreait.slack.api.test.DynamicMockGroupTests
import com.kreait.slack.api.test.MockMetaInfo
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class DynamicMockUsergroupsTests {

    @TestFactory
    fun methodInvocations(): List<DynamicTest> = DynamicMockGroupTests.methodInvocations(testCases = testCases())

    private val client = MockSlackClient()


    private fun testCases() = listOf(
            MockMetaInfo({ client.usergroups().create("") }, mock {}, SuccessfulCreateResponse.sample(), mock {}, ErrorCreateResponse.sample(), CreateRequest.sample()),
            MockMetaInfo({ client.usergroups().disable("") }, mock {}, SuccessfulDisableResponse.sample(), mock {}, ErrorDisableResponse.sample(), DisableRequest.sample()),
            MockMetaInfo({ client.usergroups().enable("") }, mock {}, SuccessfulEnableResponse.sample(), mock {}, ErrorEnableResponse.sample(), EnableRequest.sample()),
            MockMetaInfo({ client.usergroups().list("") }, mock {}, SuccessfulListResponse.sample(), mock {}, ErrorListResponse.sample(), ListRequest.sample()),
            MockMetaInfo({ client.usergroups().update("") }, mock {}, SuccessfulUpdateResponse.sample(), mock {}, ErrorUpdateResponse.sample(), UpdateRequest.sample()),
            MockMetaInfo({ client.usergroups().listAllUsers("") }, mock {}, SuccessfulUsergroupsUsersListResponse.sample(), mock {}, ErrorUsergroupsUsersListResponse.sample(), UsergroupsUsersListRequest.sample()),
            MockMetaInfo({ client.usergroups().updateUsers("") }, mock {}, SuccessfulUsergroupUsersUpdateResponse.sample(), mock {}, ErrorUsergroupUsersUpdateResponse.sample(), UsergroupUsersUpdateRequest.sample())
    )
}
