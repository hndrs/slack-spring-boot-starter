package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.contract.jackson.group.users.ConversationsRequest
import com.kreait.slack.api.contract.jackson.group.users.ErrorConversationsResponse
import com.kreait.slack.api.contract.jackson.group.users.ErrorDeletePhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.ErrorGetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.ErrorGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.ErrorIdentityResponse
import com.kreait.slack.api.contract.jackson.group.users.ErrorInfoResponse
import com.kreait.slack.api.contract.jackson.group.users.ErrorListResponse
import com.kreait.slack.api.contract.jackson.group.users.ErrorLookupByEmailResponse
import com.kreait.slack.api.contract.jackson.group.users.ErrorSetPhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.ErrorSetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.ErrorSetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.GetPresenceRequest
import com.kreait.slack.api.contract.jackson.group.users.GetProfileRequest
import com.kreait.slack.api.contract.jackson.group.users.InfoRequest
import com.kreait.slack.api.contract.jackson.group.users.ListRequest
import com.kreait.slack.api.contract.jackson.group.users.LookupByEmailRequest
import com.kreait.slack.api.contract.jackson.group.users.SetPhotoRequest
import com.kreait.slack.api.contract.jackson.group.users.SetPresenceRequest
import com.kreait.slack.api.contract.jackson.group.users.SetProfileRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulConversationsResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulDeletePhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulGetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulIdentityResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulInfoResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulListResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulLookupByEmailResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulSetPhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulSetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulSetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.sample
import com.kreait.slack.api.spring.DynamicGroupTests
import com.kreait.slack.api.spring.MetaInfo
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.springframework.web.client.RestTemplate

@DisplayName("Users Tests")
class MethodTests {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @TestFactory
    @DisplayName("Method Invocation Tests")
    fun methodInvocations(): List<DynamicTest> = DynamicGroupTests.methodInvocations(testCases = testCases(), mockTemplate = mockTemplate)

    private fun testCases() = listOf(
            MetaInfo("users.conversations", SuccessfulConversationsResponse.sample(), ErrorConversationsResponse.sample(), ConversationsRequest.sample(), DefaultUserConversationsMethod("", mockTemplate)),
            MetaInfo("users.list", SuccessfulListResponse.sample(), ErrorListResponse.sample(), ListRequest.sample(), DefaultUserListMethod("", mockTemplate)),
            MetaInfo("users.list", SuccessfulListResponse.sample(), ErrorListResponse.sample(), ListRequest.sample(), DefaultUserListMethodPaging("", mockTemplate)),
            MetaInfo("users.deletePhoto", SuccessfulDeletePhotoResponse.sample(), ErrorDeletePhotoResponse.sample(), "", DefaultUsersDeletePhotoMethod("", mockTemplate)),
            MetaInfo("users.getPresence", SuccessfulGetPresenceResponse.sample(), ErrorGetPresenceResponse.sample(), GetPresenceRequest.sample(), DefaultUsersGetPresenceMethod("", mockTemplate)),
            MetaInfo("users.profile.get", SuccessfulGetProfileResponse.sample(), ErrorGetProfileResponse.sample(), GetProfileRequest.sample(), DefaultUsersGetProfileMethod("", mockTemplate)),
            MetaInfo("users.identity", SuccessfulIdentityResponse.sample(), ErrorIdentityResponse.sample(), "", DefaultUsersIdentityMethod("", mockTemplate)),
            MetaInfo("users.info", SuccessfulInfoResponse.sample(), ErrorInfoResponse.sample(), InfoRequest.sample(), DefaultUsersInfoMethod("", mockTemplate)),
            MetaInfo("users.lookupByEmail", SuccessfulLookupByEmailResponse.sample(), ErrorLookupByEmailResponse.sample(), LookupByEmailRequest.sample(), DefaultUsersLookupByEmailMethod("", mockTemplate)),
            MetaInfo("users.setPhoto", SuccessfulSetPhotoResponse.sample(), ErrorSetPhotoResponse.sample(), SetPhotoRequest.sample(), DefaultUsersSetPhotoMethod("", mockTemplate)),
            MetaInfo("users.setPresence", SuccessfulSetPresenceResponse.sample(), ErrorSetPresenceResponse.sample(), SetPresenceRequest.sample(), DefaultUsersSetPresenceMethod("", mockTemplate)),
            MetaInfo("users.profile.set", SuccessfulSetProfileResponse.sample(), ErrorSetProfileResponse.sample(), SetProfileRequest.sample(), DefaultUsersSetProfileMethod("", mockTemplate))
    )
}
