package io.hndrs.slack.api.spring.group.usergroups

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
import io.hndrs.slack.api.spring.DynamicGroupTests
import io.hndrs.slack.api.spring.MetaInfo
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.usergroups.users.SpringUsergroupsUsersListMethod
import io.hndrs.slack.api.spring.group.usergroups.users.SpringUsergroupsUsersUpdateMethod
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.springframework.web.client.RestTemplate

@DisplayName("Usergroups Group Tests")
class MethodsTests {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
    }

    @TestFactory
    @DisplayName("Method Invocation Tests")
    fun methodInvocations(): List<DynamicTest> = DynamicGroupTests.methodInvocations(testCases = testCases(), mockTemplate = mockTemplate)

    private fun testCases() = listOf(
            MetaInfo("usergroups.create", SuccessfulCreateResponse.sample(), ErrorCreateResponse.sample(), CreateRequest.sample(), SpringUsergroupsCreateMethod("", mockTemplate)),
            MetaInfo("usergroups.disable", SuccessfulDisableResponse.sample(), ErrorDisableResponse.sample(), DisableRequest.sample(), SpringUsergroupsDisableMethod("", mockTemplate)),
            MetaInfo("usergroups.enable", SuccessfulEnableResponse.sample(), ErrorEnableResponse.sample(), EnableRequest.sample(), SpringUsergroupsEnableMethod("", mockTemplate)),
            MetaInfo("usergroups.list", SuccessfulListResponse.sample(), ErrorListResponse.sample(), ListRequest.sample(), SpringUsergroupsListMethod("", mockTemplate)),
            MetaInfo("usergroups.update", SuccessfulUpdateResponse.sample(), ErrorUpdateResponse.sample(), UpdateRequest.sample(), SpringUsergroupsUpdateMethod("", mockTemplate)),
            MetaInfo("usergroups.users.list", SuccessfulUsergroupsUsersListResponse.sample(), ErrorUsergroupsUsersListResponse.sample(), UsergroupsUsersListRequest.sample(), SpringUsergroupsUsersListMethod("", mockTemplate)),
            MetaInfo("usergroups.users.update", SuccessfulUsergroupUsersUpdateResponse.sample(), ErrorUsergroupUsersUpdateResponse.sample(), UsergroupUsersUpdateRequest.sample(), SpringUsergroupsUsersUpdateMethod("", mockTemplate))
    )
}
