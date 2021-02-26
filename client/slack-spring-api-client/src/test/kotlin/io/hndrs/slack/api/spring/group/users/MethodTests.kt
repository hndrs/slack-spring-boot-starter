package io.hndrs.slack.api.spring.group.users

import io.hndrs.slack.api.contract.jackson.group.users.ConversationsRequest
import io.hndrs.slack.api.contract.jackson.group.users.ErrorConversationsResponse
import io.hndrs.slack.api.contract.jackson.group.users.ErrorDeletePhotoResponse
import io.hndrs.slack.api.contract.jackson.group.users.ErrorGetPresenceResponse
import io.hndrs.slack.api.contract.jackson.group.users.ErrorGetProfileResponse
import io.hndrs.slack.api.contract.jackson.group.users.ErrorIdentityResponse
import io.hndrs.slack.api.contract.jackson.group.users.ErrorInfoResponse
import io.hndrs.slack.api.contract.jackson.group.users.ErrorListResponse
import io.hndrs.slack.api.contract.jackson.group.users.ErrorLookupByEmailResponse
import io.hndrs.slack.api.contract.jackson.group.users.ErrorSetPhotoResponse
import io.hndrs.slack.api.contract.jackson.group.users.ErrorSetPresenceResponse
import io.hndrs.slack.api.contract.jackson.group.users.ErrorSetProfileResponse
import io.hndrs.slack.api.contract.jackson.group.users.GetPresenceRequest
import io.hndrs.slack.api.contract.jackson.group.users.GetProfileRequest
import io.hndrs.slack.api.contract.jackson.group.users.InfoRequest
import io.hndrs.slack.api.contract.jackson.group.users.ListRequest
import io.hndrs.slack.api.contract.jackson.group.users.LookupByEmailRequest
import io.hndrs.slack.api.contract.jackson.group.users.SetPhotoRequest
import io.hndrs.slack.api.contract.jackson.group.users.SetPresenceRequest
import io.hndrs.slack.api.contract.jackson.group.users.SetProfileRequest
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulConversationsResponse
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulDeletePhotoResponse
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulGetPresenceResponse
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulGetProfileResponse
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulIdentityResponse
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulInfoResponse
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulListResponse
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulLookupByEmailResponse
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulSetPhotoResponse
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulSetPresenceResponse
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulSetProfileResponse
import io.hndrs.slack.api.contract.jackson.group.users.sample
import io.hndrs.slack.api.spring.DynamicGroupTests
import io.hndrs.slack.api.spring.MetaInfo
import io.hndrs.slack.api.spring.group.RestTemplateFactory
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
        mockTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
    }

    @TestFactory
    @DisplayName("Method Invocation Tests")
    fun methodInvocations(): List<DynamicTest> = DynamicGroupTests.methodInvocations(testCases = testCases(), mockTemplate = mockTemplate)

    private fun testCases() = listOf(
            MetaInfo("users.conversations", SuccessfulConversationsResponse.sample(), ErrorConversationsResponse.sample(), ConversationsRequest.sample(), SpringUserConversationsMethod("", mockTemplate)),
            MetaInfo("users.list", SuccessfulListResponse.sample(), ErrorListResponse.sample(), ListRequest.sample(), SpringUserListMethod("", mockTemplate)),
            MetaInfo("users.deletePhoto", SuccessfulDeletePhotoResponse.sample(), ErrorDeletePhotoResponse.sample(), "", SpringUsersDeletePhotoMethod("", mockTemplate)),
            MetaInfo("users.getPresence", SuccessfulGetPresenceResponse.sample(), ErrorGetPresenceResponse.sample(), GetPresenceRequest.sample(), SpringUsersGetPresenceMethod("", mockTemplate)),
            MetaInfo("users.profile.get", SuccessfulGetProfileResponse.sample(), ErrorGetProfileResponse.sample(), GetProfileRequest.sample(), SpringUsersGetProfileMethod("", mockTemplate)),
            MetaInfo("users.identity", SuccessfulIdentityResponse.sample(), ErrorIdentityResponse.sample(), "", SpringUsersIdentityMethod("", mockTemplate)),
            MetaInfo("users.info", SuccessfulInfoResponse.sample(), ErrorInfoResponse.sample(), InfoRequest.sample(), SpringUsersInfoMethod("", mockTemplate)),
            MetaInfo("users.lookupByEmail", SuccessfulLookupByEmailResponse.sample(), ErrorLookupByEmailResponse.sample(), LookupByEmailRequest.sample(), SpringUsersLookupByEmailMethod("", mockTemplate)),
            MetaInfo("users.setPhoto", SuccessfulSetPhotoResponse.sample(), ErrorSetPhotoResponse.sample(), SetPhotoRequest.sample(), SpringUsersSetPhotoMethod("", mockTemplate)),
            MetaInfo("users.setPresence", SuccessfulSetPresenceResponse.sample(), ErrorSetPresenceResponse.sample(), SetPresenceRequest.sample(), SpringUsersSetPresenceMethod("", mockTemplate)),
            MetaInfo("users.profile.set", SuccessfulSetProfileResponse.sample(), ErrorSetProfileResponse.sample(), SetProfileRequest.sample(), SpringUsersSetProfileMethod("", mockTemplate))
    )
}
