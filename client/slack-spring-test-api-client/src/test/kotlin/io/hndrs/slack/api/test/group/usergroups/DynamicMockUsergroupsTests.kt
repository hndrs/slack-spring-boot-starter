package io.hndrs.slack.api.test.group.usergroups

import io.hndrs.slack.api.contract.jackson.group.usergroups.CreateRequest
import io.hndrs.slack.api.contract.jackson.group.usergroups.DisableRequest
import io.hndrs.slack.api.contract.jackson.group.usergroups.EnableRequest
import io.hndrs.slack.api.contract.jackson.group.usergroups.ErrorCreateResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.ErrorDisableResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.ErrorEnableResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.ErrorListResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.ErrorUpdateResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.ListRequest
import io.hndrs.slack.api.contract.jackson.group.usergroups.SuccessfulCreateResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.SuccessfulDisableResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.SuccessfulEnableResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.SuccessfulListResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.SuccessfulUpdateResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.UpdateRequest
import io.hndrs.slack.api.contract.jackson.group.usergroups.sample
import io.hndrs.slack.api.contract.jackson.group.usergroups.users.ErrorUsergroupUsersUpdateResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.users.ErrorUsergroupsUsersListResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.users.SuccessfulUsergroupUsersUpdateResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.users.SuccessfulUsergroupsUsersListResponse
import io.hndrs.slack.api.contract.jackson.group.usergroups.users.UsergroupUsersUpdateRequest
import io.hndrs.slack.api.contract.jackson.group.usergroups.users.UsergroupsUsersListRequest
import io.hndrs.slack.api.contract.jackson.group.usergroups.users.sample
import io.hndrs.slack.api.test.DynamicMockGroupTests
import io.hndrs.slack.api.test.MockMetaInfo
import io.hndrs.slack.api.test.MockSlackClient
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
            MockMetaInfo({ client.usergroups().listGroups("") }, mock {}, SuccessfulListResponse.sample(), mock {}, ErrorListResponse.sample(), ListRequest.sample()),
            MockMetaInfo({ client.usergroups().update("") }, mock {}, SuccessfulUpdateResponse.sample(), mock {}, ErrorUpdateResponse.sample(), UpdateRequest.sample()),
            MockMetaInfo({ client.usergroups().listUsers("") }, mock {}, SuccessfulUsergroupsUsersListResponse.sample(), mock {}, ErrorUsergroupsUsersListResponse.sample(), UsergroupsUsersListRequest.sample()),
            MockMetaInfo({ client.usergroups().replaceUsers("") }, mock {}, SuccessfulUsergroupUsersUpdateResponse.sample(), mock {}, ErrorUsergroupUsersUpdateResponse.sample(), UsergroupUsersUpdateRequest.sample())
    )
}
