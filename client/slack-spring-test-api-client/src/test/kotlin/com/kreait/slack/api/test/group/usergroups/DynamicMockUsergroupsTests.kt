package com.kreait.slack.api.test.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.CreateRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.DisableRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.EnableRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorCreateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorDisableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorEnableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulCreateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulDisableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulEnableResponse
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
import com.kreait.slack.api.test.group.usergroups.users.MockUsergroupsUsersListMethod
import com.kreait.slack.api.test.group.usergroups.users.MockUsergroupsUsersUpdateMethod
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class DynamicMockUsergroupsTests {

    @TestFactory
    fun methodInvocations(): List<DynamicTest> = DynamicMockGroupTests.methodInvocations(testCases = testCases())

    private fun testCases() = listOf(
            MockMetaInfo(MockUsergroupsCreateMethod(), mock {}, SuccessfulCreateResponse.sample(), mock {}, ErrorCreateResponse.sample(), CreateRequest.sample()),
            MockMetaInfo(MockUsergroupsDisableMethod(), mock {}, SuccessfulDisableResponse.sample(), mock {}, ErrorDisableResponse.sample(), DisableRequest.sample()),
            MockMetaInfo(MockUsergroupsEnableMethod(), mock {}, SuccessfulEnableResponse.sample(), mock {}, ErrorEnableResponse.sample(), EnableRequest.sample()),
            MockMetaInfo(MockUsergroupsListMethod(), mock {}, SuccessfulUsergroupsUsersListResponse.sample(), mock {}, ErrorUsergroupsUsersListResponse.sample(), UsergroupsUsersListRequest.sample()),
            MockMetaInfo(MockUsergroupsUpdateMethod(), mock {}, SuccessfulUsergroupUsersUpdateResponse.sample(), mock {}, ErrorUsergroupUsersUpdateResponse.sample(), UsergroupUsersUpdateRequest.sample()),
            MockMetaInfo(MockUsergroupsUsersListMethod(), mock {}, SuccessfulUsergroupsUsersListResponse.sample(), mock {}, ErrorUsergroupsUsersListResponse.sample(), UsergroupsUsersListRequest.sample()),
            MockMetaInfo(MockUsergroupsUsersUpdateMethod(), mock {}, SuccessfulUsergroupUsersUpdateResponse.sample(), mock {}, ErrorUsergroupUsersUpdateResponse.sample(), UsergroupUsersUpdateRequest.sample())
    )
}