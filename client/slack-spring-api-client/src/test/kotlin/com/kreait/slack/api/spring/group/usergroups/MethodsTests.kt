package com.kreait.slack.api.spring.group.usergroups

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
import com.kreait.slack.api.spring.DynamicGroupTests
import com.kreait.slack.api.spring.MetaInfo
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.usergroups.users.SpringUsergroupsUsersListMethod
import com.kreait.slack.api.spring.group.usergroups.users.SpringUsergroupsUsersUpdateMethod
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
        mockTemplate = RestTemplateFactory.slackTemplate()
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
