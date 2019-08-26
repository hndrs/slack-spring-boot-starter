package com.kreait.slack.api.test.group.users

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
import com.kreait.slack.api.test.DynamicMockGroupTests
import com.kreait.slack.api.test.MockMetaInfo
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class DynamicUsersGroupTests {

    @TestFactory
    fun methodInvocations(): List<DynamicTest> = DynamicMockGroupTests.methodInvocations(testCases = testCases())

    private fun testCases() = listOf(
            MockMetaInfo(MockUserConversationsMethod(), mock { }, SuccessfulConversationsResponse.sample(), mock { }, ErrorConversationsResponse.sample(), ConversationsRequest.sample()),
            MockMetaInfo(MockUsersDeletePhotoMethod(), mock { }, SuccessfulDeletePhotoResponse.sample(), mock { }, ErrorDeletePhotoResponse.sample(), Unit),
            MockMetaInfo(MockUsersGetPresenceMethod(), mock { }, SuccessfulGetPresenceResponse.sample(), mock { }, ErrorGetPresenceResponse.sample(), GetPresenceRequest.sample()),
            MockMetaInfo(MockUsersGetProfileMethod(), mock { }, SuccessfulGetProfileResponse.sample(), mock { }, ErrorGetProfileResponse.sample(), GetProfileRequest.sample()),
            MockMetaInfo(MockUsersIdentityMethod(), mock { }, SuccessfulIdentityResponse.sample(), mock { }, ErrorIdentityResponse.sample(), Unit),
            MockMetaInfo(MockUsersInfoMethod(), mock { }, SuccessfulInfoResponse.sample(), mock { }, ErrorInfoResponse.sample(), InfoRequest.sample()),
            MockMetaInfo(MockUserListMethod(), mock { }, SuccessfulListResponse.sample(), mock { }, ErrorListResponse.sample(), ListRequest.sample()),
            MockMetaInfo(MockUsersLookupByEmailMethod(), mock { }, SuccessfulLookupByEmailResponse.sample(), mock { }, ErrorLookupByEmailResponse.sample(), LookupByEmailRequest.sample()),
            MockMetaInfo(MockUsersSetPhotoMethod(), mock { }, SuccessfulSetPhotoResponse.sample(), mock { }, ErrorSetPhotoResponse.sample(), SetPhotoRequest.sample()),
            MockMetaInfo(MockUsersSetPresenceMethod(), mock { }, SuccessfulSetPresenceResponse.sample(), mock { }, ErrorSetPresenceResponse.sample(), SetPresenceRequest.sample()),
            MockMetaInfo(MockUsersSetProfileMethod(), mock { }, SuccessfulSetProfileResponse.sample(), mock { }, ErrorSetProfileResponse.sample(), SetProfileRequest.sample())
    )
}